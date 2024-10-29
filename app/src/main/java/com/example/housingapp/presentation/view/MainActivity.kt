package com.example.housingapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.housingapp.App
import com.example.housingapp.R
import com.example.housingapp.databinding.ActivityMainBinding
import com.example.housingapp.di.injectViewModel
import com.example.housingapp.presentation.viewmodel.MainViewModel
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

// Главная активность приложения, которая управляет навигацией между фрагментами.
// Наследуется от AppCompatActivity и использует разметку activity_main.
class MainActivity : AppCompatActivity() {

    // Делегат для привязки элементов интерфейса с помощью ViewBinding.
    private val mViewBinding by viewBinding(ActivityMainBinding::bind)

    // Получение экземпляра ViewModel с помощью внедрения зависимостей.
    private val mViewModel: MainViewModel by injectViewModel()

    // Хранитель навигатора для управления навигацией между фрагментами.
    @Inject
    lateinit var mNavigatorHolder: NavigatorHolder

    // Метод жизненного цикла, вызываемый при создании активности.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this) // Внедрение зависимостей
        setContentView(R.layout.activity_main) // Установка разметки
        setupObservers(savedInstanceState) // Настройка наблюдателей
    }

    // Создание навигатора для управления фрагментами.
    private val mNavigator: Navigator = object : AppNavigator(this, R.id.container) {
        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands) // Применение команд навигации
            supportFragmentManager.executePendingTransactions() // Обработка транзакций
        }
    }

    // Метод жизненного цикла, вызываемый при возобновлении фрагментов.
    override fun onResumeFragments() {
        super.onResumeFragments()
        mNavigatorHolder.setNavigator(mNavigator) // Установка навигатора
    }

    // Метод жизненного цикла, вызываемый при приостановке активности.
    override fun onPause() {
        mNavigatorHolder.removeNavigator() // Удаление навигатора
        super.onPause()
    }

    // Метод для настройки наблюдателей за изменениями в ViewModel.
    private fun setupObservers(savedInstanceState: Bundle?): Unit = with(mViewBinding) {
        if (savedInstanceState == null) {
            // Наблюдение за установкой стартового фрагмента.
            mViewModel.ldSetStartFragment.observe(this@MainActivity) {
                mNavigator.applyCommands(arrayOf<Command>(Replace(it))) // Применение команды замены фрагмента
            }
        }
    }
}