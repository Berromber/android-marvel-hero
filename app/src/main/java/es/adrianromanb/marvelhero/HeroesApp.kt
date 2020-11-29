package es.adrianromanb.marvelhero

import android.app.Application

class HeroesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}