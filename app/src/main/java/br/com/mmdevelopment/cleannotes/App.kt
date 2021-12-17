package br.com.mmdevelopment.cleannotes

import android.app.Application
import br.com.mmdevelopment.cleannotes.di.dataModule
import br.com.mmdevelopment.cleannotes.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, vmModule)
        }
    }
}