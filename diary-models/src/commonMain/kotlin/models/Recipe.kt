package models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "Recipe")
@Serializable
data class Recipe(
    @PrimaryKey
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("ingredients")
    val ingredients: List<Ingredient>,

    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerialName("nutrition_values")
    val nutritionValues: NutritionValues,

    @SerialName("time_required")
    val timeRequired: TimeRequired,

    @SerialName("difficulty")
    val difficulty: Difficulty,

    @SerialName("servings")
    val servings: Int,

    @SerialName("is_public")
    val isPublic: Boolean = false,

    @SerialName("username")
    val username: String,

    @SerialName("user_id")
    val userId: String,

    @SerialName("creation_date")
    val creationDateTime: LocalDateTime,
) 