package br.com.mmdevelopment.cleannotes.di

import br.com.mmdevelopment.cleannotes.data.local.AppDatabase
import br.com.mmdevelopment.cleannotes.data.mapper.NoteEntityMapper
import br.com.mmdevelopment.cleannotes.data.mapper.NoteEntityMapperImpl
import br.com.mmdevelopment.cleannotes.data.repositoryimpl.NoteRepositoryImpl
import br.com.mmdevelopment.cleannotes.domain.repository.NoteRepository
import br.com.mmdevelopment.cleannotes.presentation.ui.viewmodel.SharedViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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

val viewModel = module {
    viewModel { SharedViewModel(get()) }
}

object AppModules {
    val modules = data + repository + mapper + viewModel
}