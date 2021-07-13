package br.com.mmdevelopment.cleannotes.datasource

class NoteRepository(private val dao: NoteDao) {

    fun insert(note: NoteEntity) {
        dao.insert(note)
    }

    fun update(note: NoteEntity){
        dao.update(note)
    }

    fun getAll() = dao.getAll()

    fun findById(noteId: Int) = dao.findById(noteId)

    fun delete(note: NoteEntity) {
        dao.delete(note)
    }
}