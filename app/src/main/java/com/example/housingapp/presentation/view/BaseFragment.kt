package com.example.housingapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

// Абстрактный класс BaseFragment, который наследуется от Fragment.
// Этот класс предоставляет базовую структуру для всех фрагментов в приложении,
// включая методы для настройки элементов интерфейса, обработки событий и
// наблюдения за изменениями данных.
abstract class BaseFragment(
    @LayoutRes private val layoutResId: Int // Ресурс разметки для фрагмента
) : Fragment(layoutResId) {

    // Метод, вызываемый после создания представления фрагмента.
    // Здесь устанавливаются слушатели, начальные значения и наблюдатели.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners() // Настройка обработчиков событий
        setViewsPresets() // Установка начальных значений элементов интерфейса
        setupObservers() // Настройка наблюдателей за данными
    }

    // Абстрактный метод для установки начальных значений элементов интерфейса.
    abstract fun setViewsPresets(): Unit

    // Абстрактный метод для настройки слушателей событий на элементы интерфейса.
    abstract fun setupListeners(): Unit

    // Абстрактный метод для настройки наблюдателей за изменениями данных.
    abstract fun setupObservers(): Unit
}