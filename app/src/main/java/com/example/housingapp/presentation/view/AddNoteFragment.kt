package com.example.housingapp.presentation.view

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.housingapp.R
import com.example.housingapp.databinding.FragmentAddNoteBinding
import com.example.housingapp.di.injectViewModel
import com.example.housingapp.presentation.viewmodel.AddNoteViewModel
import kotlinx.coroutines.launch

// Фрагмент для добавления заметок. Наследуется от BaseFragment и использует
// разметку fragment_add_note. Обеспечивает взаимодействие пользователя
// с элементами интерфейса для создания новых заметок.
class AddNoteFragment : BaseFragment(R.layout.fragment_add_note) {

    // Делегат для привязки элементов интерфейса с помощью ViewBinding.
    private val mViewBinding by viewBinding(FragmentAddNoteBinding::bind)

    // Получение экземпляра ViewModel с помощью внедрения зависимостей.
    private val mViewModel: AddNoteViewModel by injectViewModel()

    // Метод для настройки начальных значений элементов интерфейса.
    // В данном случае оставлен пустым для дальнейшей реализации.
    override fun setViewsPresets(): Unit = with(mViewBinding) {
        // Здесь можно установить начальные значения для элементов интерфейса
    }

    // Метод для настройки обработчиков событий на элементы интерфейса.
    override fun setupListeners(): Unit = with(mViewBinding) {
        // Обработчик для кнопки перехода на главный экран.
        ivHome.setOnClickListener {
            mViewModel.navigateToHome() // Переход на главный экран
        }

        // Обработчик для кнопки подтверждения.
        cvConfirm.setOnClickListener {
            // Передаем введенные данные в ViewModel для обработки.
            mViewModel.onConfirmButton(
                etShortDescription.text.toString(), // Краткое описание
                etLongDescription.text.toString(), // Полное описание
                etTelephoneNumber.text.toString(), // Номер телефона
                etPrice.text.toString(), // Цена
                etImageLink.text.toString() // Ссылка на изображение
            )
        }
    }

    // Метод для настройки наблюдателей за изменениями данных в ViewModel.
    override fun setupObservers(): Unit = with(mViewModel) {
        lifecycleScope.launch {
            // Запускаем корутину, которая будет собирать события из потока.
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                shfToastText.collect { text ->
                    // Показываем тост с текстом, полученным из ViewModel.
                    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}