package br.com.mmdevelopment.cleannotes

import android.app.Application
import br.com.mmdevelopment.cleannotes.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        insertKoin()
        insertTimber()
    }

    private fun insertTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun insertKoin() {
        startKoin {
            androidContext(this@App)
            modules(AppModules.modules)
        }
    }
}