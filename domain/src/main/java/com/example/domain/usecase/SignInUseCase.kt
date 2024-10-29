package com.example.domain.usecase

import com.example.domain.repository.SignInSignUpManager
import io.reactivex.Single

// Состояния входа в систему.
enum class SignInState {
    SIGN_IN_FAILED,
    SIGN_IN_SUCCESSFUL,
    EMPTY_EMAIL,
}

// UseCase для обработки логики входа в систему.
class SignInUseCase(private val signInSignUpManager: SignInSignUpManager) {

    @Suppress("CheckResult")
    // Выполнение логики входа с проверкой состояния.
    fun execute(email: String, password: String): Single<SignInState> {
        return Single.create<SignInState> { emitter ->
            // Проверка, что email не пустой.
            if (email.isEmpty()) {
                emitter.onSuccess(SignInState.EMPTY_EMAIL)
            } else {
                // Попытка входа с использованием менеджера.
                signInSignUpManager.signInUser(email, password).subscribe { isUserSignedUp ->
                    if (isUserSignedUp) {
                        emitter.onSuccess(SignInState.SIGN_IN_SUCCESSFUL) // Успешный вход
                    } else {
                        emitter.onSuccess(SignInState.SIGN_IN_FAILED) // Ошибка входа
                    }
                }
            }
        }
    }
}