package com.example.housingapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.SignInState
import com.example.domain.usecase.SignInUseCase
import com.example.housingapp.R
import com.example.housingapp.presentation.navigation.Screens
import com.example.housingapp.util.ResourceProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// ViewModel для экрана входа в систему. Управляет логикой входа и навигацией.
class SignInViewModel @Inject constructor(
    private val mRouter: Router, // Навигатор для управления переходами между экранами
    private val signInUseCase: SignInUseCase, // UseCase для выполнения входа
    private val resourceProvider: ResourceProvider // Провайдер ресурсов для получения строковых ресурсов
) : ViewModel() {

    // Ливдата для отображения текста в Toast-сообщениях.
    private val _ldShowToastText: MutableLiveData<String> = MutableLiveData()
    val ldShowToastText: LiveData<String> = _ldShowToastText

    // Метод для обработки нажатия кнопки регистрации.
    fun onSignUpBtn() {
        mRouter.navigateTo(Screens.signUpFragment()) // Переход на экран регистрации
    }

    @SuppressLint("CheckResult")
    // Метод для обработки нажатия кнопки входа.
    fun onSignInBtn(email: String, password: String) {
        signInUseCase.execute(email, password) // Выполнение логики входа
            .subscribeOn(Schedulers.io()) // Выполнение в фоновом потоке
            .observeOn(AndroidSchedulers.mainThread()) // Наблюдение на главном потоке
            .subscribe { resultState ->
                when (resultState!!) {
                    SignInState.SIGN_IN_FAILED -> {
                        // Ошибка входа
                        _ldShowToastText.value = resourceProvider
                            .getStringRes(R.string.failed_to_sign_in_to_the_account)
                    }
                    SignInState.EMPTY_EMAIL -> {
                        // Пустой email
                        _ldShowToastText.value = resourceProvider
                            .getStringRes(R.string.email_is_empty)
                    }
                    SignInState.SIGN_IN_SUCCESSFUL -> {
                        // Успешный вход
                        mRouter.newRootScreen(Screens.homeFragment()) // Переход на главную страницу
                    }
                }
            }
    }
}