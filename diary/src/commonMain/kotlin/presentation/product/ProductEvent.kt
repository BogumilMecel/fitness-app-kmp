package presentation.product

sealed interface ProductEvent {
    data class WeightChanged(val value: String) : ProductEvent
    object OnSaveClicked : ProductEvent
}
