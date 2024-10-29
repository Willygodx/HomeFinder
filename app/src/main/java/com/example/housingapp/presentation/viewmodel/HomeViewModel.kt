package com.example.housingapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.domain.repository.HouseItemManager
import com.example.domain.repository.SignInSignUpManager
import com.example.housingapp.presentation.mapper.toPresentation
import com.example.housingapp.presentation.model.HouseItemPresentation
import com.example.housingapp.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// ViewModel для управления логикой домашнего экрана приложения.
class HomeViewModel @Inject constructor(
    private val mRouter: Router,                       // Router для навигации между экранами.
    private val mSignInSignUpManager: SignInSignUpManager, // Менеджер для управления процессом входа/регистрации.
    private val mHouseItemManager: HouseItemManager    // Менеджер для управления элементами недвижимости.
) : ViewModel() {

    // Метод для навигации к экрану добавления заметки.
    fun navigateToAddNote() {
        mRouter.navigateTo(Screens.addNoteFragment()) // Переход к экрану добавления заметки.
    }

    // Метод для обработки выхода из аккаунта пользователя.
    @SuppressLint("CheckResult") // Отключаем предупреждение о результатах подписки.
    fun onLogout() {
        mSignInSignUpManager.logOutUser() // Вызываем метод выхода из аккаунта.
            .subscribeOn(Schedulers.io()) // Выполняем операцию в фоновом потоке.
            .observeOn(AndroidSchedulers.mainThread()) // Наблюдаем за результатами на главном потоке.
            .subscribe { isLogoutSuccessful -> // Подписываемся на результат выхода.
                if (isLogoutSuccessful) { // Если выход был успешным,
                    mRouter.newRootScreen(Screens.signInFragment()) // переходим на экран входа.
                }
            }
    }

    // Метод для обработки клика по элементу списка.
    fun onClickItem(item: HouseItemPresentation) {
        mRouter.navigateTo(Screens.detailsFragment(item)) // Переход к экрану деталей элемента.
    }

    // Метод для получения списка элементов недвижимости.
    fun getRvItems(): List<HouseItemPresentation> {
        return mHouseItemManager.getHouseItems().toPresentation() // Получаем элементы из менеджера и преобразуем их для отображения.
    }
}