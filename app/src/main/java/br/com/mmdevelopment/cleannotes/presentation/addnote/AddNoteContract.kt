package br.com.mmdevelopment.cleannotes.presentation.addnote

import br.com.mmdevelopment.cleannotes.domain.model.Note

interface AddNoteContract {

    interface View {

    }

    interface Presenter {
        fun insertNote(note: Note)
        fun detachView()
    }
}