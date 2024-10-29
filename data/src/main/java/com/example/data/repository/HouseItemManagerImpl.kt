package com.example.data.repository

import com.example.data.mapper.toData
import com.example.data.mapper.toDomain
import com.example.data.model.HouseItemData
import com.example.domain.model.HouseItemDomain
import com.example.domain.repository.HouseItemManager

class HouseItemManagerImpl : HouseItemManager {

    private val list = mutableListOf<HouseItemDomain>()

    init {
        list.add(HouseItemData(
            "https://ibogatyr.ru/upload/medialibrary/739/a52ab9eb469ea9d8ddc709b640104fa2.jpg",
            "Квартира 2ком 53м",
            "Уютная двухкомнатная квартира с современным ремонтом. Идеально подходит для семьи или пары.",
            1000,
            123
        ).toDomain())

        list.add(HouseItemData(
            "https://s0.rbk.ru/v6_top_pics/resized/1005xH/media/img/3/22/347097148538223.jpeg",
            "Квартира 1ком 40м",
            "Комфортабельная однокомнатная квартира в центре города. Близко к магазинами и общественному транспорту.",
            666,
            785474654
        ).toDomain())

        list.add(HouseItemData(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxOmwmWH0QD3dFp15LY5mKYi2t69zWLK6FqA694gQWa4mrLe8-WXCd9C3ztsU3KSwvmgo&usqp=CAU",
            "Квартира 3ком 80м",
            "Просторная трехкомнатная квартира с шикарным видом. Отличное место для комфортного проживания.",
            5000,
            365434564
        ).toDomain())

        list.add(HouseItemData(
            "https://ibogatyr.ru/upload/medialibrary/739/a52ab9eb469ea9d8ddc709b640104fa2.jpg",
            "Квартира 2ком 53м",
            "Уютная двухкомнатная квартира с современным ремонтом. Идеально подходит для семьи или пары.",
            1000,
            123
        ).toDomain())

        list.add(HouseItemData(
            "https://ibogatyr.ru/upload/medialibrary/739/a52ab9eb469ea9d8ddc709b640104fa2.jpg",
            "Квартира 2ком 53м",
            "Уютная двухкомнатная квартира с современным ремонтом. Идеально подходит для семьи или пары.",
            1000,
            123
        ).toDomain())

        list.add(HouseItemData(
            "https://ibogatyr.ru/upload/medialibrary/739/a52ab9eb469ea9d8ddc709b640104fa2.jpg",
            "Квартира 2ком 53м",
            "Уютная двухкомнатная квартира с современным ремонтом. Идеально подходит для семьи или пары.",
            1000,
            123
        ).toDomain())

        list.add(HouseItemData(
            "https://s0.rbk.ru/v6_top_pics/resized/1005xH/media/img/3/22/347097148538223.jpeg",
            "Квартира 1ком 40м",
            "Комфортабельная однокомнатная квартира в центре города. Близко к магазинами и общественному транспорту.",
            666,
            785474654
        ).toDomain())

        list.add(HouseItemData(
            "https://s0.rbk.ru/v6_top_pics/resized/1005xH/media/img/3/22/347097148538223.jpeg",
            "Квартира 1ком 40м",
            "Комфортабельная однокомнатная квартира в центре города. Близко к магазинами и общественному транспорту.",
            666,
            785474654
        ).toDomain())
    }

    override fun getHouseItems(): List<HouseItemDomain> {
        return list
    }

    override fun addHouseItem(item: HouseItemDomain) {
        list.add(item)
    }
}