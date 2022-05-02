package com.mesinger.spaceappxml

import android.app.Application
import com.mesinger.spaceappxml.di.dataModule
import com.mesinger.spaceappxml.di.firebaseModule
import com.mesinger.spaceappxml.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SpaceAppXML: Application() {
    override fun onCreate() {
        super.onCreate()
        application = this

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@SpaceAppXML)
            modules(
                viewModelModule,
                firebaseModule,
                dataModule
            )
        }

    }

    companion object{
        lateinit var application: Application
    }
}