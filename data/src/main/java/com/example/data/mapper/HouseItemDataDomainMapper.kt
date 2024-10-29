package com.example.data.mapper

import com.example.data.model.HouseItemData
import com.example.domain.model.HouseItemDomain

// Функция для преобразования HouseItemData в HouseItemDomain.
fun HouseItemData.toDomain(): HouseItemDomain {
    return HouseItemDomain(
        imageLink = this.imageLink,
        shortDescription = this.shortDescription,
        longDescription = this.longDescription,
        costPerMonth = this.costPerMonth,
        telephone = this.telephone
    )
}

// Функция для преобразования HouseItemDomain в HouseItemData.
fun HouseItemDomain.toData(): HouseItemData {
    return HouseItemData(
        imageLink = this.imageLink,
        shortDescription = this.shortDescription,
        longDescription = this.longDescription,
        costPerMonth = this.costPerMonth,
        telephone = this.telephone
    )
}

// Функция для преобразования списка HouseItemData в список HouseItemDomain.
fun List<HouseItemData>.toDomain(): List<HouseItemDomain> {
    return this.map { it.toDomain() }
}

// Функция для преобразования списка HouseItemDomain в список HouseItemData.
fun List<HouseItemDomain>.toData(): List<HouseItemData> {
    return this.map { it.toData() }
}