package com.example.housingapp.di.module

import android.content.Context
import com.example.housingapp.util.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    // Метод для предоставления объекта ResourceProvider.
    // ResourceProvider используется для получения ресурсов приложения (например, строк, цветов и т.д.)
    // Это позволяет избежать прямых обращений к контексту в других классах.
    // Аннотация @Singleton гарантирует, что только один экземпляр ResourceProvider будет создан в приложении.
    @Singleton
    @Provides
    fun provideResourceProvider(mAppContext: Context): ResourceProvider {
        // Создаем и возвращаем новый экземпляр ResourceProvider, передавая контекст приложения.
        return ResourceProvider(mAppContext) // ResourceProvider управляет доступом к ресурсам.
    }
}