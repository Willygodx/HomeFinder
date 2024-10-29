package com.example.housingapp.presentation.view

import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.housingapp.R
import com.example.housingapp.databinding.FragmentSignInBinding
import com.example.housingapp.di.injectViewModel
import com.example.housingapp.presentation.viewmodel.SignInViewModel

// Фрагмент для отображения экрана входа в систему.
// Наследует от BaseFragment и использует разметку fragment_sign_in.
class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    // Делегат для привязки элементов интерфейса с помощью ViewBinding.
    private val mViewBinding by viewBinding(FragmentSignInBinding::bind)

    // Получение экземпляра ViewModel с помощью внедрения зависимостей.
    private val mViewModel: SignInViewModel by injectViewModel()

    // Метод для установки начальных значений элементов интерфейса.
    override fun setViewsPresets() = with(mViewBinding) {
        // Здесь можно установить начальные значения для элементов интерфейса.
    }

    // Метод для настройки обработчиков событий на элементы интерфейса.
    override fun setupListeners() = with(mViewBinding) {
        // Обработчик для перехода на экран регистрации.
        tvSignUp.setOnClickListener {
            mViewModel.onSignUpBtn() // Переход на экран регистрации
        }

        // Обработчик для кнопки входа в систему.
        btnSignIn.setOnClickListener {
            // Передаем введенные данные в ViewModel для обработки.
            mViewModel.onSignInBtn(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    // Метод для настройки наблюдателей за изменениями данных в ViewModel.
    override fun setupObservers() = with(mViewModel) {
        // Наблюдение за текстом для отображения в Toast-сообщении.
        ldShowToastText.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() // Показываем тост
        }
    }
}