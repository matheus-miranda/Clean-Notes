package br.com.mmdevelopment.cleannotes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.mmdevelopment.cleannotes.data.local.dao.NoteDao
import br.com.mmdevelopment.cleannotes.data.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "notes.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}