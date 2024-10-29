package com.example.housingapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.repository.SignInSignUpManager
import com.example.housingapp.presentation.navigation.Screens
import com.example.housingapp.util.ResourceProvider
import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// ViewModel для главной активности. Управляет навигацией на основе статуса входа пользователя.
class MainViewModel @Inject constructor(
    private val mResourceProvider: ResourceProvider, // Провайдер ресурсов
    private val signInSignUpManager: SignInSignUpManager // Менеджер для управления входом и регистрацией
) : ViewModel() {

    // Ливдата для установки стартового фрагмента.
    private val _ldSetStartFragment: MutableLiveData<FragmentScreen> = MutableLiveData()
    val ldSetStartFragment: LiveData<FragmentScreen> = _ldSetStartFragment

    // Инициализация ViewModel.
    init {
        redirectIfLoggedIn() // Проверка статуса входа при инициализации
    }

    @SuppressLint("CheckResult")
    private fun redirectIfLoggedIn() {
        // Проверка, зарегистрирован ли пользователь.
        signInSignUpManager.isUserSignedUp()
            .subscribeOn(Schedulers.io()) // Выполнение в фоновом потоке
            .observeOn(AndroidSchedulers.mainThread()) // Наблюдение на главном потоке
            .subscribe { isUserSignedUp ->
                // Установка стартового фрагмента в зависимости от статуса входа.
                _ldSetStartFragment.value = if (isUserSignedUp) {
                    Screens.homeFragment() // Если пользователь зарегистрирован, переход на главную страницу
                } else {
                    Screens.signInFragment() // Если нет, переход на экран входа
                }
            }
    }
}