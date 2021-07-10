package br.com.mmdevelopment.cleannotes.datasource

import br.com.mmdevelopment.cleannotes.model.Note

object NoteDataSource {
    private val list = arrayListOf<Note>()

    fun getList() = list

    fun insertNote(note: Note) {
        list.add(note.copy(id = list.size + 1))
    }
}