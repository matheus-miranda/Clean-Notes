package br.com.mmdevelopment.cleannotes.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.mmdevelopment.cleannotes.data.local.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun getAll(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE title LIKE (:searchQuery) OR description LIKE (:searchQuery)")
    fun searchDatabase(searchQuery: String): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id = (:noteId)")
    suspend fun findById(noteId: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)
}