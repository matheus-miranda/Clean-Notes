package br.com.mmdevelopment.cleannotes.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mmdevelopment.cleannotes.datasource.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    /*companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notes_db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }*/
}