package br.com.mmdevelopment.cleannotes.datasource

import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id = (:noteId)")
    fun findById(noteId: Int): NoteEntity?

    @Update
    fun update(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)
}