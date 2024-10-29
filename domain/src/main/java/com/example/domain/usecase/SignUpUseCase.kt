package com.example.domain.usecase

import com.example.domain.repository.SignInSignUpManager
import io.reactivex.Single

enum class SignUpState {
    INVALID_PASSWORD,
    PASSWORDS_ARE_NOT_EQUAL,
    SIGN_UP_FAILED,
    SIGN_UP_SUCCESSFUL,
    EMPTY_EMAIL
}

class SignUpUseCase(private val signInSignUpManager: SignInSignUpManager) {

    @Suppress("CheckResult")
    fun execute(email: String, password: String, confirmPassword: String): Single<SignUpState> {
        //password contains at least one uppercase letter, one lowercase letter, one number and >5 symbols
        val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$")

        return Single.create<SignUpState> { emitter ->
            if (email.isEmpty()) {
                emitter.onSuccess(SignUpState.EMPTY_EMAIL)
            }
            else if (password != confirmPassword) {
                emitter.onSuccess(SignUpState.PASSWORDS_ARE_NOT_EQUAL)
            } else if (!regex.matches(password)) {
                emitter.onSuccess(SignUpState.INVALID_PASSWORD)
            } else {
                signInSignUpManager.signUpUser(email, password).subscribe { isUserSignedUp ->
                    if (isUserSignedUp) {
                        emitter.onSuccess(SignUpState.SIGN_UP_SUCCESSFUL)
                    } else {
                        emitter.onSuccess(SignUpState.SIGN_UP_FAILED)
                    }
                }
            }
        }
    }
}