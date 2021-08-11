package br.com.mmdevelopment.cleannotes.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.mmdevelopment.cleannotes.datasource.model.NoteEntity

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

    @Delete
    suspend fun delete(note: NoteEntity)
}