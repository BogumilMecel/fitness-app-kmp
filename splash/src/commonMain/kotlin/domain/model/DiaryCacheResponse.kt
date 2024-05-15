package domain.model

import domain.model.product.Product
import domain.model.product.ProductDiaryEntry
import domain.model.recipe.Recipe
import domain.model.recipe.RecipeDiaryEntry
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryCacheResponse(
    @SerialName("products")
    val products: List<Product>,

    @SerialName("product_diary_entries")
    val productDiaryEntries: List<ProductDiaryEntry>,

    @SerialName("recipes")
    val recipes: List<Recipe>,

    @SerialName("recipe_diary_entries")
    val recipeDiaryEntries: List<RecipeDiaryEntry>,
)