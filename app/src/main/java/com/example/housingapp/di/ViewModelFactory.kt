package com.example.housingapp.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.housingapp.App
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor(
    // Хранит карту, связывающую классы ViewModel с их провайдерами.
    private val models: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    // Метод для создания экземпляров ViewModel.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Получаем провайдер ViewModel для переданного класса.
        val viewModelProvider = models[modelClass]
            ?: throw IllegalArgumentException("$modelClass not found") // Выбрасываем исключение, если ViewModel не найдена.

        @Suppress("UNCHECKED_CAST")
        // Возвращаем созданный экземпляр ViewModel, приведенный к нужному типу.
        return viewModelProvider.get() as T
    }
}

// Расширение для инъекции ViewModel в Activity.
inline fun <T, reified VM : ViewModel> T.injectViewModel(
    viewModelClass: KClass<VM> = VM::class
) where T : AppCompatActivity, T : LifecycleOwner = lazy(this) {
    // Создаем ViewModelProvider с фабрикой, полученной из компонента приложения.
    ViewModelProvider(
        this,
        (this.application as App).appComponent.provideFactory() // Получаем фабрику из приложения.
    )[viewModelClass.java] // Возвращаем экземпляр ViewModel.
}

// Расширение для инъекции ViewModel в Fragment.
inline fun <T, reified VM : ViewModel> T.injectViewModel(
    viewModelClass: KClass<VM> = VM::class
) where T : Fragment, T : LifecycleOwner = lazy(this) {
    // Создаем ViewModelProvider с фабрикой, полученной из родительской активности.
    ViewModelProvider(
        this,
        (activity!!.application as App).appComponent.provideFactory() // Получаем фабрику из активности.
    )[viewModelClass.java] // Возвращаем экземпляр ViewModel.
}