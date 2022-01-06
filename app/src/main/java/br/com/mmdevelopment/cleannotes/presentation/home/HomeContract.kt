package br.com.mmdevelopment.cleannotes.presentation.home

import br.com.mmdevelopment.cleannotes.domain.model.Note

interface HomeContract {

    interface View {
        fun bindSearchView()
        fun showNotesOnRecycleView(list: List<Note>)
        fun editNoteNavigation(note: Note)
        fun showEmptyList(shouldDisplay: Boolean)
        fun addNewNote()
    }

    interface Presenter {
        fun detachView()
        fun onSearch(query: String)
        fun getAllNotes()
        fun setView(view: View)
    }
}