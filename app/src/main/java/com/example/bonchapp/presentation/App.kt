package com.example.bonchapp.presentation

import android.app.Application
import com.example.bonchapp.presentation.di.AppComponent
import com.example.bonchapp.presentation.di.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen

class App: Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        AndroidThreeTen.init(this)

    }
}
