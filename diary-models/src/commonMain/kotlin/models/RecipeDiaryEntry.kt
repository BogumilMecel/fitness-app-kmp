package models

import androidx.compose.runtime.Composable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "RecipeDiaryEntry")
@Serializable
data class RecipeDiaryEntry(
    @PrimaryKey
    @SerialName("id")
    override val id: String,

    @SerialName("nutrition_values")
    override val nutritionValues: NutritionValues,

    @SerialName("date")
    override val date: LocalDate,

    @SerialName("user_id")
    override val userId: String,

    @SerialName("meal_name")
    override val mealName: MealName,

    @SerialName("creation_date")
    override val creationDateTime: LocalDateTime,

    @SerialName("change_date")
    override val changeDateTime: LocalDateTime,

    @SerialName("recipe_name")
    val recipeName: String,

    @SerialName("recipe_id")
    val recipeId: String,

    @SerialName("servings")
    val servings: Int,

    @SerialName("deleted")
    val deleted: Boolean,
) : DiaryItem {
    @Composable
    override fun getDisplayValue() = "$servings servings"

    override fun getDiaryEntryType() = DiaryEntryType.RECIPE
} 