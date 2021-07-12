package br.com.mmdevelopment.cleannotes.datasource

import br.com.mmdevelopment.cleannotes.model.Note

object NoteDataSource {
    private val list = arrayListOf<Note>()

    fun getList() = list.toList()

    fun insertNote(note: Note) {
        if (note.id == 0) {
            list.add(note.copy(id = list.size + 1))
        } else {
            list.remove(note)
            list.add(note)
        }
    }

    fun findById(taskId: Int) = list.find { it.id == taskId }
}