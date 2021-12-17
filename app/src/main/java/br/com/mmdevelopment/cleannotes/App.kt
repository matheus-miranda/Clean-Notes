package br.com.mmdevelopment.cleannotes

import android.app.Application
import br.com.mmdevelopment.cleannotes.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        insertKoin()
    }

    private fun insertKoin() {
        startKoin {
            androidContext(this@App)
            modules(AppModules.modules)
        }
    }
}