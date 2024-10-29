package com.example.housingapp

import android.app.Application
import com.example.housingapp.di.AppComponent
import com.example.housingapp.di.DaggerAppComponent

// Основной класс приложения, который инициализирует Dagger компонент.
class App : Application() {

    // Lazy инициализация компонента Dagger.
    val appComponent: AppComponent by lazy(
        DaggerAppComponent
            .builder()
            .applicationContext(this)::build
    )

    override fun onCreate() {
        super.onCreate()
        instance = this // Установка статического экземпляра приложения.
    }

    companion object {
        // Статический экземпляр приложения для доступа из других частей кода.
        internal lateinit var instance: App
            private set
    }
}