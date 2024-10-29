package com.example.housingapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.housingapp.App
import com.example.housingapp.R
import com.example.housingapp.databinding.RvItemHouseAdBinding
import com.example.housingapp.presentation.model.HouseItemPresentation
import com.example.housingapp.util.ResourceProvider
import com.squareup.picasso.Picasso
import javax.inject.Inject

// Адаптер для RecyclerView, предназначенный для отображения элементов недвижимости.
class RvItemsAdapter(private val mListener: ClickListener) :
    RecyclerView.Adapter<RvItemsAdapter.CategoryHolder>() {

    // Список элементов, которые будут отображаться в RecyclerView.
    private val rvItems = mutableListOf<HouseItemPresentation>()

    // Внутренний класс для представления элемента списка.
    class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Провайдер ресурсов, инъектируемый с помощью Dagger.
        @Inject
        lateinit var resourceProvider: ResourceProvider

        // Связывание представления элемента с использованием View Binding.
        private val mBinding = RvItemHouseAdBinding.bind(view)

        // Инициализация CategoryHolder, где происходит инъекция зависимостей.
        init {
            // Инъекция зависимостей в текущий объект CategoryHolder.
            App.instance.appComponent.inject(this)
        }

        // Метод для привязки данных элемента недвижимости к элементу списка.
        fun bind(item: HouseItemPresentation, listener: ClickListener) = with(mBinding) {
            // Загружаем изображение элемента с помощью библиотеки Picasso.
            Picasso.get()
                .load(item.imageLink) // URL изображения.
                .placeholder(R.drawable.im_flat_placeholder) // Плейсхолдер, показываемый до загрузки.
                .into(riv) // Указываем ImageView для отображения изображения.

            // Устанавливаем цену элемента.
            tvPrice.text = item.costPerMonth.toString()
            // Устанавливаем описание элемента.
            tvDescription.text = item.shortDescription

            // Устанавливаем слушатель клика на элемент списка.
            itemView.setOnClickListener {
                listener.onClickRvItem(item) // Вызываем метод клика с переданным элементом.
            }
        }
    }

    // Создание нового держателя (ViewHolder) для элемента списка.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        // Инфляция макета элемента списка из XML.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_house_ad, parent, false)
        return CategoryHolder(view) // Возвращаем новый экземпляр CategoryHolder.
    }

    // Привязка данных к уже созданному держателю (ViewHolder).
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        // Привязываем данные элемента из списка к держателю.
        holder.bind(rvItems[position], mListener)
    }

    // Возвращает общее количество элементов в списке.
    override fun getItemCount(): Int {
        return rvItems.size
    }

    // Метод для добавления новых элементов в адаптер.
    fun addHouseItems(categoriesToAdd: List<HouseItemPresentation>) {
        // Очищаем текущий список элементов.
        rvItems.clear()
        // Добавляем новые элементы.
        rvItems.addAll(categoriesToAdd)
        // Уведомляем адаптер об изменении данных для обновления интерфейса.
        notifyDataSetChanged()
    }

    // Интерфейс для обработки кликов на элементе списка.
    interface ClickListener {
        fun onClickRvItem(item: HouseItemPresentation) // Метод, вызываемый при клике на элемент.
    }
}