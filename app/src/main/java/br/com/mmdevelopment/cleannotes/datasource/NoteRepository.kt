package br.com.mmdevelopment.cleannotes.datasource

import br.com.mmdevelopment.cleannotes.datasource.model.NoteEntity

class NoteRepository(private val dao: NoteDao) {

    fun insert(note: NoteEntity) {
        dao.insert(note)
    }

    fun search(searchQuery: String): List<NoteEntity> {
        return dao.searchDatabase(searchQuery)
    }

    fun getAll() = dao.getAll()

    fun findById(noteId: Int) = dao.findById(noteId)

    fun delete(note: NoteEntity) {
        dao.delete(note)
    }
}