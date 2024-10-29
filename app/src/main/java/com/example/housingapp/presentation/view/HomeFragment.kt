package com.example.housingapp.presentation.view

import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.housingapp.R
import com.example.housingapp.databinding.FragmentHomeBinding
import com.example.housingapp.di.injectViewModel
import com.example.housingapp.presentation.adapter.RvItemsAdapter
import com.example.housingapp.presentation.model.HouseItemPresentation
import com.example.housingapp.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

// Фрагмент для отображения главного экрана приложения.
// Наследует от BaseFragment и использует разметку fragment_home.
// Реализует интерфейс ClickListener для обработки кликов по элементам списка.
class HomeFragment : BaseFragment(R.layout.fragment_home), RvItemsAdapter.ClickListener {

    // Делегат для привязки элементов интерфейса с помощью ViewBinding.
    private val mViewBinding by viewBinding(FragmentHomeBinding::bind)

    // Получение экземпляра ViewModel с помощью внедрения зависимостей.
    private val mViewModel: HomeViewModel by injectViewModel()

    // Адаптер для отображения элементов списка домов.
    private val mRvAdapter: RvItemsAdapter = RvItemsAdapter(this)

    // Метод для установки начальных значений элементов интерфейса.
    override fun setViewsPresets(): Unit = with(mViewBinding) {
        rvList.adapter = mRvAdapter // Установка адаптера для RecyclerView
        mRvAdapter.addHouseItems(mViewModel.getRvItems()) // Добавление элементов в адаптер
    }

    // Метод для настройки обработчиков событий на элементы интерфейса.
    override fun setupListeners(): Unit = with(mViewBinding) {
        // Обработчик для кнопки добавления заметки.
        ivAddNote.setOnClickListener {
            mViewModel.navigateToAddNote() // Переход на экран добавления заметки
        }
        // Обработчик для кнопки выхода из аккаунта.
        fabLogout.setOnClickListener {
            mViewModel.onLogout() // Выход из аккаунта
        }
    }

    // Метод для настройки наблюдателей за изменениями данных в ViewModel.
    override fun setupObservers(): Unit = with(mViewModel) {
        // В данном случае наблюдатели не настроены,
        // можно добавить их при необходимости.
    }

    // Реализация метода ClickListener для обработки кликов по элементам списка.
    override fun onClickRvItem(item: HouseItemPresentation) {
        mViewModel.onClickItem(item) // Обработка клика по элементу
    }
}