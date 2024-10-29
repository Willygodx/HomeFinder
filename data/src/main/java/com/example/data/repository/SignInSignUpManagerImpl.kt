package com.example.data.repository

import com.example.domain.repository.SignInSignUpManager
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single

class SignInSignUpManagerImpl : SignInSignUpManager {

    override fun isUserSignedUp(): Single<Boolean> {
        return Single.create { emitter ->
            emitter.onSuccess(FirebaseAuth.getInstance().currentUser != null)
        }
    }

    override fun signInUser(email: String, password: String): Single<Boolean> {
        return Single.create { emitter ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    emitter.onSuccess(true)
                }
                .addOnFailureListener {
                    emitter.onSuccess(false)
                }
        }
    }

    override fun signUpUser(email: String, password: String): Single<Boolean> {
        return Single.create { emitter ->
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    emitter.onSuccess(true)
                }
                .addOnFailureListener {
                    emitter.onSuccess(false)
                }
        }
    }

    override fun logOutUser(): Single<Boolean> {
        return Single.create { emitter ->
            FirebaseAuth.getInstance().signOut()
            emitter.onSuccess(FirebaseAuth.getInstance().currentUser == null)
        }
    }

}