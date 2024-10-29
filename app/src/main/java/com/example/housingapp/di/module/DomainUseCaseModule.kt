package com.example.housingapp.di.module

import com.example.domain.repository.SignInSignUpManager
import com.example.domain.usecase.SignInUseCase
import com.example.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainUseCaseModule {

    // Метод для предоставления экземпляра SignUpUseCase.
    // Этот метод принимает SignInSignUpManager в качестве зависимости,
    // что позволяет использовать его для обработки логики регистрации.
    @Provides
    fun provideSignUpUseCase(signInSignUpManager: SignInSignUpManager): SignUpUseCase {
        // Создаем и возвращаем новый экземпляр SignUpUseCase,
        // инициализируя его с помощью переданного экземпляра SignInSignUpManager.
        return SignUpUseCase(signInSignUpManager)
    }

    // Метод для предоставления экземпляра SignInUseCase.
    // Этот метод также принимает SignInSignUpManager в качестве зависимости,
    // что позволяет использовать его для обработки логики входа.
    @Provides
    fun provideSignInUseCase(signInSignUpManager: SignInSignUpManager): SignInUseCase {
        // Создаем и возвращаем новый экземпляр SignInUseCase,
        // инициализируя его с помощью переданного экземпляра SignInSignUpManager.
        return SignInUseCase(signInSignUpManager)
    }
}