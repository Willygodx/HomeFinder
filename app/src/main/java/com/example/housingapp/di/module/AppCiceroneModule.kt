package com.example.housingapp.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppCiceroneModule {
    // Создаем экземпляр Cicerone, который управляет навигацией в приложении.
    // Cicerone является основным классом, который инкапсулирует логику навигации.
    private val cicerone: Cicerone<Router> = Cicerone.create()

    // Метод для предоставления объекта Router.
    // Router отвечает за управление навигацией (переходами) между экранами приложения.
    // Аннотация @Singleton гарантирует, что только один экземпляр Router будет создан в приложении.
    @Singleton
    @Provides
    fun provideRouter(): Router = cicerone.router

    // Метод для предоставления объекта NavigatorHolder.
    // NavigatorHolder хранит информацию о текущем состоянии навигации и позволяет отслеживать,
    // какой экран в данный момент активен.
    // Также аннотирован как @Singleton для обеспечения единственного экземпляра.
    @Singleton
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
}