package com.example.domain.repository

import com.example.domain.model.HouseItemDomain

interface HouseItemManager {

    fun getHouseItems(): List<HouseItemDomain>

    fun addHouseItem(item: HouseItemDomain)
}