package domain.model

import domain.model.product.Product
import kotlinx.serialization.SerialName

data class DiaryCacheResponse(
    @SerialName("products")
    val products: List<Product>,

    @SerialName("product_diary_entries")
    val productDiaryEntries: List<Product>,

    @SerialName("recipes")
    val recipes: List<Product>,

    @SerialName("recipe_diary_entries")
    val recipeDiaryEntries: List<Product>,
)