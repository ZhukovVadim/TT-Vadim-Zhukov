package com.vadim.ttvadimzhukov

import android.annotation.SuppressLint
import android.app.Application
import com.vadim.ttvadimzhukov.di.components.AppComponent
import com.vadim.ttvadimzhukov.di.components.DaggerAppComponent
import com.vadim.ttvadimzhukov.di.module.AppModule

class App : Application() {

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        //Инициализация дагера------------------------------------------------------------------------------------------
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}