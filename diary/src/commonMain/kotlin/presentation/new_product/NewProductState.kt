package presentation.new_product

import androidx.compose.runtime.Stable

@Stable
data class NewProductState(
    val productName: String = "",
    val barcode: String = "",
    val containerWeight: String = "",
    val calories: String = "",
    val carbohydrates: String = "",
    val protein: String = "",
    val fat: String = "",
    val selectedNutritionValuesChoice: NutritionValuesChoice = NutritionValuesChoice.IN_100_GRAMS,
) 