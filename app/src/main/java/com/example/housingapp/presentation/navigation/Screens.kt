package com.example.housingapp.presentation.navigation

import com.example.housingapp.presentation.model.HouseItemPresentation
import com.example.housingapp.presentation.view.AddNoteFragment
import com.example.housingapp.presentation.view.DetailsFragment
import com.example.housingapp.presentation.view.HomeFragment
import com.example.housingapp.presentation.view.SignInFragment
import com.example.housingapp.presentation.view.SignUpFragment
import com.example.housingapp.util.withArguments
import com.github.terrakok.cicerone.androidx.FragmentScreen

// Ключ для передачи аргумента с информацией о доме между фрагментами.
const val HOUSE_ITEM_ARGUMENT_KEY = "house_item"

// Объект Screens содержит функции для создания экранов приложения.
// Каждая функция возвращает FragmentScreen, который описывает соответствующий фрагмент.
object Screens {
    // Функция для создания экрана входа в систему.
    fun signInFragment() = FragmentScreen { SignInFragment() }

    // Функция для создания экрана регистрации.
    fun signUpFragment() = FragmentScreen { SignUpFragment() }

    // Функция для создания главного экрана приложения.
    fun homeFragment() = FragmentScreen { HomeFragment() }

    // Функция для создания экрана деталей дома.
    // Принимает объект HouseItemPresentation и передает его как аргумент.
    fun detailsFragment(item: HouseItemPresentation) = FragmentScreen {
        DetailsFragment().withArguments(
            HOUSE_ITEM_ARGUMENT_KEY to item // Передаем объект как аргумент
        )
    }

    // Функция для создания экрана добавления заметки.
    fun addNoteFragment() = FragmentScreen { AddNoteFragment() }
}