package data.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import domain.model.Product

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
    ): List<Product>

    // Insert or replace product
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    // Get product by ID
    @Query("SELECT * FROM SqlProduct WHERE id = :productId LIMIT 1")
    suspend fun getProduct(productId: String): Product?
}