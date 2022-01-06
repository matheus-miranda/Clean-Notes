package br.com.mmdevelopment.cleannotes.presentation.home

import android.util.Log
import br.com.mmdevelopment.cleannotes.domain.repository.NoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomePresenter(private var view: HomeContract.View?, private val repository: NoteRepository) :
    HomeContract.Presenter {

    override fun setView(view: HomeContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onSearch(query: String) {
        view?.let {
            val result = repository.search(query)
        }
    }

    override fun getAllNotes() {
        view?.let { homeView ->
            repository.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { list ->
                        homeView.showNotesOnRecycleView(list)
                    },
                    { error ->
                        Log.e("HomePresenter", "Error getting notes ${error.message}")
                    }
                )
        }
    }
}