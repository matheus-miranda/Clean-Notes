package br.com.mmdevelopment.cleannotes.datasource

import androidx.room.*
import br.com.mmdevelopment.cleannotes.datasource.model.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id = (:noteId)")
    fun findById(noteId: Int): NoteEntity?

    @Query("SELECT * FROM NoteEntity WHERE title LIKE (:searchQuery) OR description LIKE (:searchQuery)")
    fun searchDatabase(searchQuery: String): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)
}