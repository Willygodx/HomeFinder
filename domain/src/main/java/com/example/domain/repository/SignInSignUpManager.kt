package com.example.domain.repository

import io.reactivex.Single

interface SignInSignUpManager {

    fun isUserSignedUp(): Single<Boolean>

    fun signInUser(email: String, password: String): Single<Boolean>

    fun signUpUser(email: String, password: String): Single<Boolean>

    fun logOutUser(): Single<Boolean>
}