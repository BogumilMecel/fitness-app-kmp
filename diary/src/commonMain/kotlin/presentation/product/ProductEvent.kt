package presentation.product

sealed interface ProductEvent {
    data class WeightChanged(val value: String) : ProductEvent
    data class OnQuickWeightButtonClicked(val value: Int) : ProductEvent
    object OnSaveClicked : ProductEvent
    object OnBackPressed : ProductEvent
}
