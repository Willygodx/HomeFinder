package com.example.housingapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.housingapp.R
import com.example.housingapp.databinding.FragmentSignUpBinding
import com.example.housingapp.di.injectViewModel
import com.example.housingapp.presentation.viewmodel.SignUpViewModel

// Фрагмент для отображения экрана регистрации.
// Наследует от BaseFragment и использует разметку fragment_sign_up.
class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    // Делегат для привязки элементов интерфейса с помощью ViewBinding.
    private val mViewBinding by viewBinding(FragmentSignUpBinding::bind)

    // Получение экземпляра ViewModel с помощью внедрения зависимостей.
    private val mViewModel: SignUpViewModel by injectViewModel()

    // Метод жизненного цикла, вызываемый после создания представления фрагмента.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners() // Настройка обработчиков событий
        setViewsPresets() // Установка начальных значений элементов интерфейса
        setupObservers() // Настройка наблюдателей
    }

    // Метод для установки начальных значений элементов интерфейса.
    override fun setViewsPresets() = with(mViewBinding) {
        // Здесь можно установить начальные значения для элементов интерфейса.
    }

    // Метод для настройки обработчиков событий на элементы интерфейса.
    override fun setupListeners() = with(mViewBinding) {
        // Обработчик для перехода на экран входа в систему.
        tvSignIn.setOnClickListener {
            mViewModel.onSignInBtn() // Переход на экран входа
        }

        // Обработчик для кнопки регистрации.
        btnSignUn.setOnClickListener {
            // Передаем введенные данные в ViewModel для обработки регистрации.
            mViewModel.onSignUpBtn(
                etEmail.text.toString(), // Электронная почта
                etPassword.text.toString(), // Пароль
                etConfirmPassword.text.toString() // Подтверждение пароля
            )
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