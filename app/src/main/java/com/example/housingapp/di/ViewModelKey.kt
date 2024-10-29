package com.example.housingapp.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,            // Аннотация может быть применена к функциям.
    AnnotationTarget.PROPERTY_GETTER,     // Аннотация может быть применена к геттерам свойств.
    AnnotationTarget.PROPERTY_SETTER       // Аннотация может быть применена к сеттерам свойств.
)
@Retention(AnnotationRetention.RUNTIME)      // Аннотация будет доступна во время выполнения.
@MapKey                                    // Указывает, что данная аннотация может использоваться в качестве ключа в карте.
annotation class ViewModelKey(val value: KClass<out ViewModel>) // Определение аннотации с параметром KClass.