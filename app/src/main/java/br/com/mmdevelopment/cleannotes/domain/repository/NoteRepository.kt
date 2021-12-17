package br.com.mmdevelopment.cleannotes.domain.repository

import androidx.lifecycle.LiveData
import br.com.mmdevelopment.cleannotes.domain.model.Note

interface NoteRepository {
    fun getNotes(): LiveData<List<Note>>
    fun search(searchQuery: String): LiveData<List<Note>>
    suspend fun findById(noteId: Int): Note?
    suspend fun insert(note: Note)
    suspend fun delete(note: Note)
}