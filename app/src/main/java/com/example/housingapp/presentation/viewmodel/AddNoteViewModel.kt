package com.example.housingapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.HouseItemManager
import com.example.housingapp.R
import com.example.housingapp.presentation.mapper.toDomain
import com.example.housingapp.presentation.model.HouseItemPresentation
import com.example.housingapp.presentation.navigation.Screens
import com.example.housingapp.util.ResourceProvider
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel для управления логикой добавления новой заметки (элемента недвижимости).
class AddNoteViewModel @Inject constructor(
    private val mRouter: Router,                  // Router для навигации между экранами.
    private val mHouseItemManager: HouseItemManager, // Менеджер для управления элементами недвижимости.
    private val mResourceProvider: ResourceProvider // Провайдер ресурсов для получения строк и ресурсов.
) : ViewModel() {

    // MutableSharedFlow для управления текстом уведомлений (тостов).
    private val _shfToastText = MutableSharedFlow<String>(
        replay = 1, // Запоминает одно последнее значение.
        extraBufferCapacity = 0, // Не выделяет дополнительный буфер.
        onBufferOverflow = BufferOverflow.DROP_OLDEST // Удаляет самое старое значение при переполнении.
    )

    // SharedFlow для наблюдения за текстом уведомлений (тостов).
    val shfToastText: SharedFlow<String> = _shfToastText

    // Метод для обработки нажатия кнопки подтверждения.
    fun onConfirmButton(
        shortDescription: String, // Краткое описание элемента.
        longDescription: String,  // Полное описание элемента.
        number: String,           // Номер элемента.
        price: String,            // Цена элемента.
        imageLink: String         // Ссылка на изображение элемента.
    ) {
        // Проверка на наличие пустых полей.
        if (shortDescription.isEmpty()
            || longDescription.isEmpty()
            || number.isEmpty()
            || price.isEmpty()
            || imageLink.isEmpty()
        ) {
            // Если есть пустые поля, отправляем уведомление с ошибкой.
            viewModelScope.launch {
                // Эмитируем текст уведомления о том, что некоторые поля пустые.
                _shfToastText.emit(mResourceProvider.getStringRes(R.string.some_value_is_empty))
            }
        } else {
            // Если все поля заполнены, отправляем уведомление об успешном добавлении.
            viewModelScope.launch {
                // Эмитируем текст уведомления об успешном добавлении.
                _shfToastText.emit(mResourceProvider.getStringRes(R.string.successful))
            }
            // Добавляем новый элемент недвижимости через менеджер.
            mHouseItemManager.addHouseItem(
                HouseItemPresentation(
                    imageLink = imageLink, // Ссылка на изображение.
                    shortDescription = shortDescription, // Краткое описание.
                    longDescription = longDescription, // Полное описание.
                    price.toInt(), // Преобразуем строку цены в целое число.
                    number.toInt()  // Преобразуем строку номера в целое число.
                ).toDomain()
            ) // Преобразовываем к доменной модели.
        }
    }

    // Метод для навигации обратно на домашний экран.
    fun navigateToHome() {
        mRouter.exit() // Выход из текущего экрана, возвращаясь на предыдущий.
    }
}