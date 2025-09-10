package api

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import models.Product
import models.ProductDiaryEntry
import models.Recipe
import models.RecipeDiaryEntry

@Dao
interface DiaryDao {
    @Query("""
        SELECT * FROM Product
        WHERE (:userId IS NULL OR userId = :userId)
        AND (:searchText IS NULL OR name LIKE '%' || :searchText || '%')
        ORDER BY creationDateTime DESC
        LIMIT :limit OFFSET :offset
    """)
    fun getProducts(
        userId: String?,
        searchText: String?,
        limit: Long,
        offset: Long
    ): List<Product>

    // Insert or replace product
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertProducts(products: List<Product>)

    // Insert or replace product
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertProduct(product: Product)

    // Get product by ID
    @Query("SELECT * FROM Product WHERE id = :productId LIMIT 1")
    suspend fun getProduct(productId: String): Product?

    // Insert single product diary entry
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertProductDiaryEntries(productDiaryEntries: List<ProductDiaryEntry>)

    // Insert single product diary entry
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertProductDiaryEntry(entry: ProductDiaryEntry)

    // Get product diary entries with search
    @Query("SELECT * FROM ProductDiaryEntry WHERE (:searchText IS NULL OR productName LIKE '%' || :searchText || '%') GROUP BY productId ORDER BY creationDateTime DESC LIMIT :limit OFFSET :offset")
    suspend fun getProductDiaryEntries(searchText: String?, limit: Int, offset: Int): List<ProductDiaryEntry>

    // Get all product diary entries
    @Query("SELECT * FROM ProductDiaryEntry ORDER BY creationDateTime DESC LIMIT :limit")
    fun getAllProductDiaryEntries(limit: Long): Flow<List<ProductDiaryEntry>>

    // Get product diary entries by date
    @Query("SELECT * FROM ProductDiaryEntry WHERE date = :date ORDER BY creationDateTime DESC")
    fun getProductDiaryEntriesByDate(date: LocalDate): Flow<List<ProductDiaryEntry>>

    // Delete a product diary entry
    @Delete
    suspend fun deleteProductDiaryEntry(entry: ProductDiaryEntry)

    // Delete product diary entries by date
    @Query("DELETE FROM ProductDiaryEntry WHERE date = :date")
    suspend fun deleteProductDiaryEntriesByDate(date: LocalDate)
//
//    // Get product diary entries' nutrition values by date
//    @Query("SELECT nutritionValues FROM ProductDiaryEntry WHERE date = :date")
//    suspend fun getProductDiaryEntriesNutritionValues(date: LocalDate): List<NutritionValues>

    // Get user recipes
    @Query("""
        SELECT * FROM Recipe
        WHERE (:userId IS NULL OR userId = :userId)
        AND (:searchText IS NULL OR name LIKE '%' || :searchText || '%')
        ORDER BY creationDateTime DESC LIMIT :limit OFFSET :offset
    """)
    fun getUserRecipes(userId: String?, searchText: String?, limit: Long, offset: Long): List<Recipe>

    // Insert or replace a recipe
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    // Insert or replace a recipe
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertRecipes(recipe: List<Recipe>)

    // Get recipe by ID
    @Query("SELECT * FROM Recipe WHERE id = :recipeId LIMIT 1")
    suspend fun getRecipe(recipeId: String): Recipe?

    // Insert single recipe diary entry
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertRecipeDiaryEntries(recipeDiaryEntries: List<RecipeDiaryEntry>)

    // Insert single recipe diary entry
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertRecipeDiaryEntry(entry: RecipeDiaryEntry)

    // Get recipe diary entries with search
    @Query("""
        SELECT * FROM RecipeDiaryEntry
        WHERE (:searchText IS NULL OR recipeName LIKE '%' || :searchText || '%')
        GROUP BY recipeId
        ORDER BY creationDateTime DESC LIMIT :limit OFFSET :offset
    """)
    suspend fun getRecipeDiaryEntries(searchText: String?, limit: Int, offset: Int): List<RecipeDiaryEntry>

    // Get all recipe diary entries
    @Query("SELECT * FROM RecipeDiaryEntry ORDER BY creationDateTime DESC LIMIT :limit")
    fun getAllRecipeDiaryEntries(limit: Long): Flow<List<RecipeDiaryEntry>>

    // Get recipe diary entries by date
    @Query("SELECT * FROM RecipeDiaryEntry WHERE date = :date ORDER BY creationDateTime DESC")
    fun getRecipeDiaryEntriesByDate(date: LocalDate): Flow<List<RecipeDiaryEntry>>

    // Delete a recipe diary entry
    @Delete
    suspend fun deleteRecipeDiaryEntry(entry: RecipeDiaryEntry)

    // Delete recipe diary entries by date
    @Query("DELETE FROM RecipeDiaryEntry WHERE date = :date")
    suspend fun deleteRecipeDiaryEntriesByDate(date: LocalDate)

//    // Get recipe diary entries' nutrition values by date
//    @Query("SELECT nutrition_values FROM RecipeDiaryEntry WHERE date = :date")
//    suspend fun getRecipeDiaryEntriesNutritionValues(date: LocalDate): List<NutritionValues>
}