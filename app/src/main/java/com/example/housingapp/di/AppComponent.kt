package com.example.housingapp.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.housingapp.di.module.AppCiceroneModule
import com.example.housingapp.di.module.AppModule
import com.example.housingapp.di.module.AppViewModelModule
import com.example.housingapp.di.module.DomainRepositoryModule
import com.example.housingapp.di.module.DomainUseCaseModule
import com.example.housingapp.presentation.adapter.RvItemsAdapter
import com.example.housingapp.presentation.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
[AppCiceroneModule::class, // Модуль для управления навигацией
    AppViewModelModule::class, // Модуль для предоставления ViewModel
    AppModule::class,          // Модуль для предоставления ресурсов приложения
    DomainUseCaseModule::class, // Модуль для предоставления классов бизнес-логики (Use Cases)
    DomainRepositoryModule::class] // Модуль для предоставления менеджеров данных (Repositories)
)
interface AppComponent {

    // Вложенный интерфейс для сборки компонента.
    @Component.Builder
    interface Builder {

        // Метод для привязки контекста приложения к компоненту.
        // @BindsInstance позволяет передать экземпляр при создании компонента.
        @BindsInstance
        fun applicationContext(context: Context): Builder

        // Метод для построения экземпляра AppComponent.
        fun build(): AppComponent
    }

    // Метод для предоставления фабрики ViewModel.
    // Позволяет другим классам получать экземпляры ViewModel.
    fun provideFactory(): ViewModelProvider.Factory

    // Метод для инъекции зависимостей в MainActivity.
    // Позволяет Dagger автоматически разрешать зависимости в этом классе.
    fun inject(mainActivity: MainActivity)

    // Метод для инъекции зависимостей в CategoryHolder адаптера.
    // Позволяет Dagger автоматически разрешать зависимости в этом классе.
    fun inject(categoryHolder: RvItemsAdapter.CategoryHolder)
}