package br.com.mmdevelopment.cleannotes.di

import br.com.mmdevelopment.cleannotes.data.local.AppDatabase
import br.com.mmdevelopment.cleannotes.data.repositoryimpl.NoteRepositoryImpl
import br.com.mmdevelopment.cleannotes.presentation.ui.viewmodel.SharedViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val data = module {
    single { AppDatabase.getInstance(context = androidApplication()) }
    single { get<AppDatabase>().noteDao() }
}

val repository = module {
    factory { NoteRepositoryImpl(dao = get()) }
}

val viewModel = module {
    viewModel { SharedViewModel(get()) }
}

object AppModules {
    val modules = data + repository + viewModel
}