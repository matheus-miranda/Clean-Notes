package br.com.mmdevelopment.cleannotes.presentation.home

import br.com.mmdevelopment.cleannotes.domain.repository.NoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class HomePresenter(private var view: HomeContract.View?, private val repository: NoteRepository) :
    HomeContract.Presenter {

    override fun setView(view: HomeContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onSearch(query: String) {
        view?.let { homeView ->
            repository.search("%$query%")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        homeView.showNotesOnRecycleView(it)
                    },
                    { error ->
                        Timber.e("Error searching notes " + error.message)
                    }
                )
        }
    }

    override fun getAllNotes() {
        view?.let { homeView ->
            repository.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { list ->
                        if (list.isNotEmpty()) homeView.showEmptyList(false)
                        else homeView.showEmptyList(true)
                        homeView.showNotesOnRecycleView(list)
                    },
                    { error ->
                        Timber.e("Error getting notes " + error.message)
                    }
                )
        }
    }
}