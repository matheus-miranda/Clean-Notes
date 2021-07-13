package br.com.mmdevelopment.cleannotes.datasource

import androidx.room.*
import br.com.mmdevelopment.cleannotes.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getAll(): List<Note>

    @Query("SELECT * FROM NoteEntity WHERE id = (:noteId)")
    fun findById(noteId: Int)

    @Update
    fun update(note: Note)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)
}