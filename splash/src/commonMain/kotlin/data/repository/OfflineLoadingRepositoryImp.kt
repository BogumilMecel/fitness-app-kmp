package data.repository

import domain.model.product.Product
import domain.model.product.ProductDiaryEntry
import domain.model.recipe.Recipe
import domain.model.recipe.RecipeDiaryEntry
import domain.repository.OfflineLoadingRepository

class OfflineLoadingRepositoryImp: OfflineLoadingRepository {
    override fun getProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun getRecipes(): List<Recipe> {
        TODO("Not yet implemented")
    }

    override fun getProductDiaryEntries(): List<ProductDiaryEntry> {
        TODO("Not yet implemented")
    }

    override fun getRecipeDiaryEntries(): List<RecipeDiaryEntry> {
        TODO("Not yet implemented")
    }

}