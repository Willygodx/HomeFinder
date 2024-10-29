package com.example.housingapp.presentation.mapper

import com.example.domain.model.HouseItemDomain
import com.example.housingapp.presentation.model.HouseItemPresentation

// Расширение функции для преобразования HouseItemPresentation в HouseItemDomain.
// Эта функция создает экземпляр HouseItemDomain, используя свойства,
// которые присутствуют в HouseItemPresentation.
fun HouseItemPresentation.toDomain(): HouseItemDomain {
    return HouseItemDomain(
        imageLink = this.imageLink, // Ссылка на изображение
        shortDescription = this.shortDescription, // Краткое описание
        longDescription = this.longDescription, // Полное описание
        costPerMonth = this.costPerMonth, // Стоимость в месяц
        telephone = this.telephone // Телефон для связи
    )
}

// Расширение функции для преобразования HouseItemDomain в HouseItemPresentation.
// Эта функция создает экземпляр HouseItemPresentation, используя свойства,
// которые присутствуют в HouseItemDomain.
fun HouseItemDomain.toPresentation(): HouseItemPresentation {
    return HouseItemPresentation(
        imageLink = this.imageLink, // Ссылка на изображение
        shortDescription = this.shortDescription, // Краткое описание
        longDescription = this.longDescription, // Полное описание
        costPerMonth = this.costPerMonth, // Стоимость в месяц
        telephone = this.telephone // Телефон для связи
    )
}

// Расширение функции для преобразования списка HouseItemPresentation в список HouseItemDomain.
// Эта функция использует метод map для применения toDomain() к каждому элементу списка.
fun List<HouseItemPresentation>.toDomain(): List<HouseItemDomain> {
    return this.map { it.toDomain() } // Преобразуем каждый элемент списка
}

// Расширение функции для преобразования списка HouseItemDomain в список HouseItemPresentation.
// Эта функция использует метод map для применения toPresentation() к каждому элементу списка.
fun List<HouseItemDomain>.toPresentation(): List<HouseItemPresentation> {
    return this.map { it.toPresentation() } // Преобразуем каждый элемент списка
}