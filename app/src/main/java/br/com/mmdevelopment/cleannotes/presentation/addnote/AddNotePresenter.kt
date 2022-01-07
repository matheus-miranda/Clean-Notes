package br.com.mmdevelopment.cleannotes.presentation.addnote

import br.com.mmdevelopment.cleannotes.domain.model.Note
import br.com.mmdevelopment.cleannotes.domain.repository.NoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class AddNotePresenter(
    private var view: AddNoteContract.View?, private val repository: NoteRepository
) : AddNoteContract.Presenter {

    override fun insertNote(note: Note) {
        repository.insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onComplete, Throwable::printStackTrace)
    }

    override fun setView(view: AddNoteContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun getNoteById(id: Int) {
        view?.let { addNoteView ->
            repository.findById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        addNoteView.populateFields(it)
                    },
                    {
                        Timber.e("Error getting note by id: ${it.message}")
                    }
                )
        }
    }

    private fun onComplete() {
        Timber.e("successful db insert")
    }

}