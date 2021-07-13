package br.com.mmdevelopment.cleannotes.datasource

import br.com.mmdevelopment.cleannotes.model.Note

class NoteRepository(private val dao: NoteDao) {

    fun insert(note: Note) {
        dao.insert(note)
    }

    fun getAll() = dao.getAll()

    fun findById(noteId: Int) {
        dao.findById(noteId)
    }

    fun delete(note: Note) {
        dao.delete(note)
    }
}