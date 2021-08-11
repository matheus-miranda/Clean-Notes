package br.com.mmdevelopment.cleannotes.datasource.di

import androidx.room.Room
import br.com.mmdevelopment.cleannotes.datasource.AppDatabase
import br.com.mmdevelopment.cleannotes.datasource.NoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration().build()
    }

    single {
        get<AppDatabase>().noteDao()
    }

    single {
        NoteRepository(get())
    }
}