package presentation.new_product

sealed interface NewProductEvent {
    object BackPressed : NewProductEvent
    data class ProductNameChanged(val value: String) : NewProductEvent
    data class BarcodeChanged(val value: String) : NewProductEvent
    data class ContainerWeightChanged(val value: String) : NewProductEvent
    data class CaloriesChanged(val value: String) : NewProductEvent
    data class CarbohydratesChanged(val value: String) : NewProductEvent
    data class ProteinChanged(val value: String) : NewProductEvent
    data class FatChanged(val value: String) : NewProductEvent
    data class NutritionValuesChoiceChanged(val choice: NutritionValuesChoice) : NewProductEvent
    object CreateProductClicked : NewProductEvent
} 