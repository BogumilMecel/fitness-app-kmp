package domain.model.recipe

import domain.model.DiaryItem
import domain.model.MealName
import domain.model.NutritionValues
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDiaryEntry(
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

    @SerialName("recipe_name")
    val recipeName: String,

    @SerialName("recipe_id")
    val recipeId: String,

    @SerialName("servings")
    val servings: Int,

    // TODO: remove when deleting is handled with device id
    @SerialName("deleted")
    val deleted: Boolean,

    @SerialName("creation_date")
    override val creationDateTime: LocalDateTime,

    @SerialName("change_date")
    override val changeDateTime: LocalDateTime
) : DiaryItem