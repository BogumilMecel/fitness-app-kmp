package data.api

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import domain.model.NutritionValues
import domain.model.Product
import domain.model.ProductDiaryEntry
import domain.model.Recipe
import domain.model.RecipeDiaryEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

@Dao
interface DiaryDao {
    @Query("""
        SELECT * FROM SqlProduct
        WHERE (:userId IS NULL OR userId = :userId)
        AND (:searchText IS NULL OR name LIKE '%' || :searchText || '%')
        ORDER BY creationDateTime DESC
        LIMIT :limit OFFSET :offset
    """)
    suspend fun getProducts(
        userId: String?,
        searchText: String?,
        limit: Long,
        offset: Long
    ): Flow<List<Product>>

    // Insert or replace product
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    // Get product by ID
    @Query("SELECT * FROM SqlProduct WHERE id = :productId LIMIT 1")
    suspend fun getProduct(productId: String): Product?


    // Insert single product diary entry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductDiaryEntry(entry: ProductDiaryEntry)

    // Get product diary entries with search
    @Query("SELECT * FROM ProductDiaryEntry WHERE (:searchText IS NULL OR product_name LIKE '%' || :searchText || '%') GROUP BY product_id ORDER BY creationDateTime DESC LIMIT :limit OFFSET :offset")
    suspend fun getProductDiaryEntries(searchText: String?, limit: Int, offset: Int): List<ProductDiaryEntry>

    // Get all product diary entries
    @Query("SELECT * FROM ProductDiaryEntry ORDER BY creationDateTime DESC LIMIT :limit")
    suspend fun getAllProductDiaryEntries(limit: Int): List<ProductDiaryEntry>

    // Get product diary entries by date
    @Query("SELECT * FROM ProductDiaryEntry WHERE date = :date ORDER BY creationDateTime DESC")
    suspend fun getProductDiaryEntriesByDate(date: LocalDate): List<ProductDiaryEntry>

    // Delete a product diary entry
    @Delete
    suspend fun deleteProductDiaryEntry(entry: ProductDiaryEntry)

    // Delete product diary entries by date
    @Query("DELETE FROM ProductDiaryEntry WHERE date = :date")
    suspend fun deleteProductDiaryEntriesByDate(date: LocalDate)

    // Get product diary entries' nutrition values by date
    @Query("SELECT nutritionValues FROM ProductDiaryEntry WHERE date = :date")
    suspend fun getProductDiaryEntriesNutritionValues(date: LocalDate): List<NutritionValues>

    // Get user recipes
    @Query("""
        SELECT * FROM Recipe
        WHERE (:userId IS NULL OR user_id = :userId)
        AND (:searchText IS NULL OR name LIKE '%' || :searchText || '%')
        ORDER BY creationDateTime DESC LIMIT :limit OFFSET :offset
    """)
    suspend fun getUserRecipes(userId: String?, searchText: String?, limit: Int, offset: Int): List<Recipe>

    // Insert or replace a recipe
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    // Get recipe by ID
    @Query("SELECT * FROM Recipe WHERE id = :recipeId LIMIT 1")
    suspend fun getRecipe(recipeId: String): Recipe?

    // Insert single recipe diary entry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeDiaryEntry(entry: RecipeDiaryEntry)

    // Get recipe diary entries with search
    @Query("""
        SELECT * FROM RecipeDiaryEntry
        WHERE (:searchText IS NULL OR recipe_name LIKE '%' || :searchText || '%')
        GROUP BY recipe_id
        ORDER BY creationDateTime DESC LIMIT :limit OFFSET :offset
    """)
    suspend fun getRecipeDiaryEntries(searchText: String?, limit: Int, offset: Int): List<RecipeDiaryEntry>

    // Get all recipe diary entries
    @Query("SELECT * FROM RecipeDiaryEntry ORDER BY creationDateTime DESC LIMIT :limit")
    suspend fun getAllRecipeDiaryEntries(limit: Int): List<RecipeDiaryEntry>

    // Get recipe diary entries by date
    @Query("SELECT * FROM RecipeDiaryEntry WHERE date = :date ORDER BY creationDateTime DESC")
    suspend fun getRecipeDiaryEntriesByDate(date: LocalDate): List<RecipeDiaryEntry>

    // Delete a recipe diary entry
    @Delete
    suspend fun deleteRecipeDiaryEntry(entry: RecipeDiaryEntry)

    // Delete recipe diary entries by date
    @Query("DELETE FROM RecipeDiaryEntry WHERE date = :date")
    suspend fun deleteRecipeDiaryEntriesByDate(date: LocalDate)

    // Get recipe diary entries' nutrition values by date
    @Query("SELECT nutrition_values FROM RecipeDiaryEntry WHERE date = :date")
    suspend fun getRecipeDiaryEntriesNutritionValues(date: LocalDate): List<NutritionValues>
}