package br.com.mmdevelopment.cleannotes.data.local.dao

import androidx.room.*
import br.com.mmdevelopment.cleannotes.data.local.entity.NoteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun getAll(): Flowable<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flowable<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id = (:noteId)")
    fun findById(noteId: Int): Maybe<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity): Completable

    @Update
    fun update(note: NoteEntity): Completable

    @Delete
    fun delete(note: NoteEntity): Completable
}