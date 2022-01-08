package br.com.mmdevelopment.cleannotes.presentation.home

import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import br.com.mmdevelopment.cleannotes.domain.model.Note

interface HomeContract {

    interface View {
        fun bindSearchView(searchView: SearchView)
        fun showNotesOnRecycleView(list: List<Note>)
        fun editNoteNavigation(note: Note)
        fun showEmptyList(shouldDisplay: Boolean)
        fun addNewNote()
        fun swipeToDelete()
        fun bindListeners()
        fun setIcon(menuItem: MenuItem)
        fun setRvManager()
    }

    interface Presenter {
        fun detachView()
        fun onSearch(query: String)
        fun getAllNotes()
        fun setView(view: View)
        fun delete(note: Note)
        fun insertNote(note: Note)
    }
}