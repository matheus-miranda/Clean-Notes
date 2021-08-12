package br.com.mmdevelopment.cleannotes.ui.di

import br.com.mmdevelopment.cleannotes.ui.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel {
        SharedViewModel(get())
    }
}