package br.com.mmdevelopment.cleannotes.domain.repository

import br.com.mmdevelopment.cleannotes.domain.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

interface NoteRepository {
    fun getNotes(): Flowable<List<Note>>
    fun search(searchQuery: String): Flowable<List<Note>>
    fun findById(noteId: Int): Maybe<Note>
    fun insert(note: Note): Completable
    fun update(note: Note): Completable
    fun delete(note: Note): Completable
}