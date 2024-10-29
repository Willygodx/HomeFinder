package com.example.housingapp.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.util.TypedValue
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AttrRes
import androidx.core.content.res.ResourcesCompat

// Провайдер ресурсов, обеспечивающий удобный доступ к строкам, анимациям, цветам и шрифтам.
class ResourceProvider(private val mAppContext: Context) {

    // Получение строкового ресурса по идентификатору.
    fun getStringRes(id: Int): String {
        return mAppContext.getString(id)
    }

    // Получение анимации по идентификатору.
    fun getAnim(id: Int): Animation {
        return AnimationUtils.loadAnimation(mAppContext, id)
    }

    // Получение цвета по идентификатору.
    fun getColor(id: Int): Int {
        return mAppContext.getColor(id)
    }

    // Получение шрифта по идентификатору.
    fun getFont(id: Int): Typeface {
        return ResourcesCompat.getFont(mAppContext, id)!!
    }

    // Получение цвета атрибута из темы.
    fun getAttrColor(@AttrRes attributeId: Int, context: Context): ColorStateList {
        // Знаю, что передавать контекст как параметр — плохая практика, но с mAppContext это не работает.
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attributeId, typedValue, true)
        return ColorStateList.valueOf(typedValue.data)
    }
}