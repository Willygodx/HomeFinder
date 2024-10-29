package com.example.housingapp.util

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import java.io.Serializable
import kotlin.reflect.KClass

// Получение аргумента из Bundle с выбросом исключения, если аргумент не найден.
inline fun <reified T : Any> Bundle?.getArgument(key: String): T = findArgument(key)
    ?: throw NullPointerException("Argument with key = $key not found")

// Поиск аргумента в Bundle с поддержкой различных типов.
@Suppress("IMPLICIT_CAST_TO_ANY")
inline fun <reified T : Any> Bundle?.findArgument(key: String): T? = findArgument(key) {
    val type = T::class

    val argument = when {
        type == String::class -> getString(key)
        type == Int::class -> getInt(key, -1)
        type == Long::class -> getLong(key, -1L)
        type == Boolean::class -> getBoolean(key, false)
        type == Set::class -> getSerializable(key)
        type == List::class -> getSerializable(key)
        type == CharSequence::class -> getCharSequence(key)
        type isAssign Parcelable::class -> getParcelable(key)
        type isAssign Serializable::class -> getSerializable(key)
        else -> throw IllegalArgumentException("Unsupported argument type = ${type.simpleName}")
    } as? T

    when (argument) {
        -1 -> null
        else -> argument
    }
}

// Проверка, является ли данный класс производным от другого.
infix fun KClass<*>.isAssign(type: KClass<*>): Boolean = type.javaObjectType
    .isAssignableFrom(this.javaObjectType)

// Поиск аргумента с использованием предоставленной лямбды.
inline fun <reified T : Any> Bundle?.findArgument(key: String, getArgument: Bundle.() -> T?): T? =
    this?.let { if (it.containsKey(key)) it.getArgument() else null }

// Расширение для установки аргументов в фрагмент.
fun <T : Fragment> T.withArguments(vararg args: Pair<String, Any?>): T = apply {
    arguments?.putAll(bundleOf(*args)) ?: run { arguments = bundleOf(*args) }
}

// Получение аргумента из фрагмента.
inline fun <reified T : Any> Fragment.getArgument(key: String): T = arguments.getArgument(key)

// Поиск аргумента в фрагменте.
inline fun <reified T : Any> Fragment.findArgument(key: String): T? = arguments.findArgument(key)

// Безопасный поиск аргумента в фрагменте с обработчиком.
inline fun <reified T : Any> Fragment.findArgumentSafe(key: String, handler: (T) -> Unit) {
    arguments.findArgument<T>(key)?.let { handler.invoke(it) }
}