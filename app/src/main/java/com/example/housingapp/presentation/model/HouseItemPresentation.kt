package com.example.housingapp.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Дата-класс для представления информации о доме в пользовательском интерфейсе.
// Класс реализует интерфейс Parcelable, что позволяет передавать его экземпляры
// между компонентами Android, такими как активити и фрагменты.
@Parcelize
data class HouseItemPresentation(
    val imageLink: String, // Ссылка на изображение дома
    val shortDescription: String, // Краткое описание дома
    val longDescription: String, // Полное описание дома
    val costPerMonth: Int, // Стоимость аренды в месяц
    val telephone: Int // Телефонный номер для связи
) : Parcelable