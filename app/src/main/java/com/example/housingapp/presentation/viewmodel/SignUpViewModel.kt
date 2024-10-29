package com.example.housingapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.SignUpState
import com.example.domain.usecase.SignUpUseCase
import com.example.housingapp.R
import com.example.housingapp.presentation.navigation.Screens
import com.example.housingapp.util.ResourceProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// ViewModel для экрана регистрации. Управляет логикой регистрации и навигацией.
class SignUpViewModel @Inject constructor(
    private val mRouter: Router, // Навигатор для управления переходами между экранами
    private val signUpUseCase: SignUpUseCase, // UseCase для выполнения регистрации
    private val resourceProvider: ResourceProvider // Провайдер ресурсов для получения строковых ресурсов
) : ViewModel() {

    // Ливдата для отображения текста в Toast-сообщениях.
    private val _ldShowToastText: MutableLiveData<String> = MutableLiveData()
    val ldShowToastText: LiveData<String> = _ldShowToastText

    // Метод для обработки нажатия кнопки входа.
    fun onSignInBtn() {
        mRouter.exit() // Выход из текущего экрана
    }

    @SuppressLint("CheckResult")
    // Метод для обработки нажатия кнопки регистрации.
    fun onSignUpBtn(email: String, password: String, confirmPassword: String) {
        signUpUseCase.execute(email, password, confirmPassword) // Выполнение логики регистрации
            .subscribeOn(Schedulers.io()) // Выполнение в фоновом потоке
            .observeOn(AndroidSchedulers.mainThread()) // Наблюдение на главном потоке
            .subscribe { resultState ->
                when (resultState!!) {
                    SignUpState.INVALID_PASSWORD -> {
                        // Неверный пароль
                        _ldShowToastText.value = resourceProvider
                            .getStringRes(R.string.the_password_must_contain_at_least)
                    }
                    SignUpState.PASSWORDS_ARE_NOT_EQUAL -> {
                        // Пароли не совпадают
                        _ldShowToastText.value = resourceProvider
                            .getStringRes(R.string.passwords_are_not_equal)
                    }
                    SignUpState.EMPTY_EMAIL -> {
                        // Пустой email
                        _ldShowToastText.value = resourceProvider
                            .getStringRes(R.string.email_is_empty)
                    }
                    SignUpState.SIGN_UP_FAILED -> {
                        // Ошибка регистрации
                        _ldShowToastText.value = resourceProvider
                            .getStringRes(R.string.failed_to_create_an_account)
                    }
                    SignUpState.SIGN_UP_SUCCESSFUL -> {
                        // Успешная регистрация
                        mRouter.newRootScreen(Screens.homeFragment()) // Переход на главную страницу
                    }
                }
            }
    }
}