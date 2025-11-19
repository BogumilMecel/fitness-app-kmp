package utils

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import domain.model.SelectorItem
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import models.Product

val ProductParameterType = object : NavType<Product>(isNullableAllowed = false) {

    override fun get(bundle: SavedState, key: String): Product? =
        bundle.read { parseValue(getString(key)) }

    override fun put(bundle: SavedState, key: String, value: Product) {
        bundle.write { putString(key, serializeAsValue(value)) }
    }

    override fun parseValue(value: String): Product = Json.decodeFromString(value)

    override fun serializeAsValue(value: Product): String = Json.encodeToString(value)
}

val LocalDateParameterType = object : NavType<LocalDate>(isNullableAllowed = false) {

    override fun get(bundle: SavedState, key: String): LocalDate? =
        bundle.read { parseValue(getString(key)) }

    override fun put(bundle: SavedState, key: String, value: LocalDate) {
        bundle.write { putString(key, serializeAsValue(value)) }
    }

    override fun parseValue(value: String): LocalDate = Json.decodeFromString(value)

    override fun serializeAsValue(value: LocalDate): String = Json.encodeToString(value)
}

val SelectorItemListParameterType = object : NavType<List<SelectorItem>>(isNullableAllowed = false) {

    override fun get(bundle: SavedState, key: String): List<SelectorItem>? =
        bundle.read { parseValue(getString(key)) }

    override fun put(bundle: SavedState, key: String, value: List<SelectorItem>) {
        bundle.write { putString(key, serializeAsValue(value)) }
    }

    override fun parseValue(value: String): List<SelectorItem> = Json.decodeFromString(value)

    override fun serializeAsValue(value: List<SelectorItem>): String = Json.encodeToString(value)
}