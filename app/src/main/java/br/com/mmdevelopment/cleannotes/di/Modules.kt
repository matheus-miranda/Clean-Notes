package br.com.mmdevelopment.cleannotes.di

import br.com.mmdevelopment.cleannotes.data.local.AppDatabase
import br.com.mmdevelopment.cleannotes.data.mapper.NoteEntityMapper
import br.com.mmdevelopment.cleannotes.data.mapper.NoteEntityMapperImpl
import br.com.mmdevelopment.cleannotes.data.repositoryimpl.NoteRepositoryImpl
import br.com.mmdevelopment.cleannotes.domain.repository.NoteRepository
import br.com.mmdevelopment.cleannotes.presentation.addnote.AddNoteContract
import br.com.mmdevelopment.cleannotes.presentation.addnote.AddNotePresenter
import br.com.mmdevelopment.cleannotes.presentation.home.HomeContract
import br.com.mmdevelopment.cleannotes.presentation.home.HomePresenter
import br.com.mmdevelopment.cleannotes.presentation.view.fragment.AddNoteFragment
import br.com.mmdevelopment.cleannotes.presentation.view.fragment.HomeFragment
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val data = module {
    single { AppDatabase.getInstance(context = androidApplication()) }
}

val repository = module {
    factory<NoteRepository> { NoteRepositoryImpl(db = get(), mapper = get()) }
}

val mapper = module {
    factory<NoteEntityMapper> { NoteEntityMapperImpl() }
}

val presenter = module {
    factory<HomeContract.Presenter> { HomePresenter(view = HomeFragment(), repository = get()) }
    factory<AddNoteContract.Presenter> { AddNotePresenter(view = AddNoteFragment(), repository = get())}
}

object AppModules {
    val modules = data + repository + mapper + presenter
}