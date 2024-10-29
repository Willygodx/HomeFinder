package com.example.housingapp.presentation.view

import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.housingapp.R
import com.example.housingapp.databinding.FragmentDetailsBinding
import com.example.housingapp.di.injectViewModel
import com.example.housingapp.presentation.model.HouseItemPresentation
import com.example.housingapp.presentation.navigation.HOUSE_ITEM_ARGUMENT_KEY
import com.example.housingapp.presentation.viewmodel.DetailsViewModel
import com.example.housingapp.util.getArgument
import com.squareup.picasso.Picasso

// Фрагмент для отображения деталей дома. Наследует от BaseFragment и использует
// разметку fragment_details. Отображает информацию о конкретном доме.
class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    // Делегат для привязки элементов интерфейса с помощью ViewBinding.
    private val mViewBinding by viewBinding(FragmentDetailsBinding::bind)

    // Получение экземпляра ViewModel с помощью внедрения зависимостей.
    private val mViewModel: DetailsViewModel by injectViewModel()

    // Ленивая инициализация объекта HouseItemPresentation из аргументов фрагмента.
    private val mHouseItem: HouseItemPresentation by lazy(LazyThreadSafetyMode.NONE) {
        getArgument(HOUSE_ITEM_ARGUMENT_KEY) // Получаем аргумент по ключу
    }

    // Метод для установки начальных значений элементов интерфейса.
    override fun setViewsPresets() = with(mViewBinding) {
        // Загружаем изображение дома с помощью библиотеки Picasso.
        Picasso.get()
            .load(mHouseItem.imageLink) // Ссылка на изображение
            .placeholder(R.drawable.im_flat_placeholder) // Заполнитель во время загрузки
            .into(pvPhoto) // Установка изображения в ImageView

        // Устанавливаем текстовые значения для остальных элементов интерфейса.
        tvShortDescription.text = mHouseItem.shortDescription // Краткое описание
        tvLongDescription.text = mHouseItem.longDescription // Полное описание
        tvPrice.text = mHouseItem.costPerMonth.toString() // Цена
        tvNumber.text = "${mHouseItem.telephone}" // Телефонный номер
    }

    // Метод для настройки обработчиков событий на элементы интерфейса.
    override fun setupListeners() = with(mViewBinding) {
        // В данном случае обработчики событий не установлены,
        // можно добавить их при необходимости.
    }

    // Метод для настройки наблюдателей за изменениями данных в ViewModel.
    override fun setupObservers() = with(mViewModel) {
        // В данном случае наблюдатели не настроены,
        // можно добавить их при необходимости.
    }
}