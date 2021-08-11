package br.com.mmdevelopment.cleannotes.datasource

import androidx.lifecycle.LiveData
import br.com.mmdevelopment.cleannotes.datasource.model.NoteEntity

class NoteRepository(private val dao: NoteDao) {

    val getAll: LiveData<List<NoteEntity>> = dao.getAll()

    fun search(searchQuery: String): LiveData<List<NoteEntity>> = dao.searchDatabase(searchQuery)

    suspend fun findById(noteId: Int) = dao.findById(noteId)

    suspend fun insert(note: NoteEntity) = dao.insert(note)

    suspend fun delete(note: NoteEntity) = dao.delete(note)
}