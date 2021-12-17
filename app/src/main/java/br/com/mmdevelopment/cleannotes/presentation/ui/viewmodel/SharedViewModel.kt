package br.com.mmdevelopment.cleannotes.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mmdevelopment.cleannotes.data.repositoryimpl.NoteRepositoryImpl
import br.com.mmdevelopment.cleannotes.data.local.entity.NoteEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SharedViewModel(private val repository: NoteRepositoryImpl) : ViewModel() {

    var isLinearLayoutManager = true

    val getAll: LiveData<List<NoteEntity>> = repository.getAll

    fun search(searchQuery: String): LiveData<List<NoteEntity>> {
        return repository.search(searchQuery)
    }

    fun findById(noteId: Int): NoteEntity? = runBlocking { repository.findById(noteId) }

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