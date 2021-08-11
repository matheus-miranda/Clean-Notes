package br.com.mmdevelopment.cleannotes

import android.app.Application
import br.com.mmdevelopment.cleannotes.datasource.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule)
        }
    }
}