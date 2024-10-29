package com.example.housingapp.di.module

import com.example.data.repository.HouseItemManagerImpl
import com.example.data.repository.SignInSignUpManagerImpl
import com.example.domain.repository.HouseItemManager
import com.example.domain.repository.SignInSignUpManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainRepositoryModule {

    // Метод для предоставления экземпляра SignInSignUpManager.
    // Аннотация @Singleton гарантирует, что будет создан только один экземпляр SignInSignUpManager в приложении.
    @Singleton
    @Provides
    fun provideSignInSignUpManager(): SignInSignUpManager {
        // Создаем и возвращаем новый экземпляр SignInSignUpManagerImpl,
        // который реализует интерфейс SignInSignUpManager и содержит бизнес-логику для входа и регистрации пользователей.
        return SignInSignUpManagerImpl()
    }

    // Метод для предоставления экземпляра HouseItemManager.
    // Также аннотирован как @Singleton для обеспечения единственного экземпляра.
    @Singleton
    @Provides
    fun provideHouseItemManager(): HouseItemManager {
        // Создаем и возвращаем новый экземпляр HouseItemManagerImpl,
        // который реализует интерфейс HouseItemManager и управляет элементами недвижимости.
        return HouseItemManagerImpl()
    }
}