package domain.repository

import domain.model.product.Product
import domain.model.product.ProductDiaryEntry
import domain.model.recipe.Recipe
import domain.model.recipe.RecipeDiaryEntry

interface OfflineLoadingRepository {
    fun getProducts(): List<Product>
    fun getRecipes(): List<Recipe>
    fun getProductDiaryEntries(): List<ProductDiaryEntry>
    fun getRecipeDiaryEntries(): List<RecipeDiaryEntry>
}