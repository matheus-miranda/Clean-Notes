package br.com.mmdevelopment.cleannotes.presentation.addnote

import br.com.mmdevelopment.cleannotes.domain.model.Note

interface AddNoteContract {

    interface View {
        fun findNoteById()
        fun populateFields(note: Note)
    }

    interface Presenter {
        fun setView(view: View)
        fun detachView()
        fun insertNote(note: Note)
        fun getNoteById(id: Int)
    }
}