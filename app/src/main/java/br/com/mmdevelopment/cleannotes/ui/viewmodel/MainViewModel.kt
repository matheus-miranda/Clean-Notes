package br.com.mmdevelopment.cleannotes.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mmdevelopment.cleannotes.datasource.NoteRepository
import br.com.mmdevelopment.cleannotes.datasource.model.NoteEntity
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NoteRepository) : ViewModel() {

    var isLinearLayoutManager = true

    private var noteIds: NoteEntity? = NoteEntity(0, title = "", description = "",date =  "",time = "")

    val getAll: LiveData<List<NoteEntity>> = repository.getAll

    fun search(searchQuery: String): LiveData<List<NoteEntity>> {
        return repository.search(searchQuery)
    }

    fun findById(noteId: Int): NoteEntity? {
        viewModelScope.launch {
            try {
                noteIds = repository.findById(noteId)
            } catch (e: Exception) {
                Log.e("ViewModel", e.toString())
            }
        }
        return noteIds
    }

    fun insert(note: NoteEntity) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun delete(note: NoteEntity) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }
}