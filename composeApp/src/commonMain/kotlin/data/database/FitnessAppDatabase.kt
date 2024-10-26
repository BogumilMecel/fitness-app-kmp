package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.api.DiaryDao
import domain.model.IngredientsConverter
import domain.model.NutritionValuesTypeConverter
import domain.model.Product
import domain.model.ProductDiaryEntry
import domain.model.Recipe
import domain.model.RecipeDiaryEntry
import utils.LocalDateConverter
import utils.LocalDateTimeConverter

@Database(
    entities = [Product::class, Recipe::class, ProductDiaryEntry::class, RecipeDiaryEntry::class],
    version = 1,
)
@TypeConverters(value = [NutritionValuesTypeConverter::class, LocalDateConverter::class, LocalDateTimeConverter::class, IngredientsConverter::class])
abstract class FitnessAppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "fitness_app_database.db"
    }

    abstract val diaryDao: DiaryDao
}