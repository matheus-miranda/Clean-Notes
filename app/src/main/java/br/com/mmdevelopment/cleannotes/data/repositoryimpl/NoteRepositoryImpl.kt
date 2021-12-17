package br.com.mmdevelopment.cleannotes.data.repositoryimpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import br.com.mmdevelopment.cleannotes.data.local.AppDatabase
import br.com.mmdevelopment.cleannotes.data.mapper.NoteEntityMapper
import br.com.mmdevelopment.cleannotes.domain.model.Note
import br.com.mmdevelopment.cleannotes.domain.repository.NoteRepository

class NoteRepositoryImpl(db: AppDatabase, private val mapper: NoteEntityMapper) : NoteRepository {

    private val dao = db.noteDao()

    override fun getNotes(): LiveData<List<Note>> = dao.getAll().map { mapper.toDomainList(it) }

    override fun search(searchQuery: String): LiveData<List<Note>> =
        dao.searchDatabase(searchQuery).map { mapper.toDomainList(it) }

    override suspend fun findById(noteId: Int) = dao.findById(noteId)?.let { mapper.toDomain(it) }

    override suspend fun insert(note: Note) = dao.insert(mapper.toEntity(note))

    override suspend fun delete(note: Note) = dao.delete(mapper.toEntity(note))
}