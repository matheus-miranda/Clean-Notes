package br.com.mmdevelopment.cleannotes.di

import br.com.mmdevelopment.cleannotes.presentation.ui.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel {
        SharedViewModel(get())
    }
}