package br.com.mmdevelopment.cleannotes.presentation.addnote

import android.util.Log
import br.com.mmdevelopment.cleannotes.domain.model.Note
import br.com.mmdevelopment.cleannotes.domain.repository.NoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AddNotePresenter(
    private var view: AddNoteContract.View?, private val repository: NoteRepository
) : AddNoteContract.Presenter {

    override fun insertNote(note: Note) {
        repository.insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onComplete, Throwable::printStackTrace)
    }

    override fun detachView() {
        view = null
    }

    private fun onComplete() {
        Log.e("AddNotePresenter", "successful db insert")
    }

}