package br.com.mmdevelopment.cleannotes.data.repositoryimpl

import br.com.mmdevelopment.cleannotes.data.local.AppDatabase
import br.com.mmdevelopment.cleannotes.data.mapper.NoteEntityMapper
import br.com.mmdevelopment.cleannotes.domain.model.Note
import br.com.mmdevelopment.cleannotes.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

class NoteRepositoryImpl(db: AppDatabase, private val mapper: NoteEntityMapper) : NoteRepository {

    private val dao = db.noteDao()

    override fun getNotes(): Flowable<List<Note>> = dao.getAll().map { mapper.toDomainList(it) }

    override fun search(searchQuery: String): Flowable<List<Note>> =
        dao.searchDatabase(searchQuery).map { mapper.toDomainList(it) }

    override fun findById(noteId: Int): Maybe<Note> =
        dao.findById(noteId).map { mapper.toDomain(it) }

    override fun insert(note: Note): Completable = dao.insert(mapper.toEntity(note))

    override fun update(note: Note): Completable = dao.update(mapper.toEntity(note))

    override fun delete(note: Note): Completable = dao.delete(mapper.toEntity(note))
}