package utils

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import domain.model.SelectorItem
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import models.Product

val ProductParameterType = object : NavType<Product>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Product? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun put(bundle: Bundle, key: String, value: Product) {
        bundle.putString(key, serializeAsValue(value))
    }

    override fun parseValue(value: String): Product = Json.decodeFromString(value)

    override fun serializeAsValue(value: Product): String = Json.encodeToString(value)
}

val LocalDateParameterType = object : NavType<LocalDate>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): LocalDate? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun put(bundle: Bundle, key: String, value: LocalDate) {
        bundle.putString(key, serializeAsValue(value))
    }

    override fun parseValue(value: String): LocalDate = Json.decodeFromString(value)

    override fun serializeAsValue(value: LocalDate): String = Json.encodeToString(value)
}

val SelectorItemListParameterType = object : NavType<List<SelectorItem>>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): List<SelectorItem>? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun put(bundle: Bundle, key: String, value: List<SelectorItem>) {
        bundle.putString(key, serializeAsValue(value))
    }

    override fun parseValue(value: String): List<SelectorItem> = Json.decodeFromString(value)

    override fun serializeAsValue(value: List<SelectorItem>): String = Json.encodeToString(value)
}