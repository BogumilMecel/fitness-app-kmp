package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import api.DiaryDao
import models.NutritionValuesTypeConverter
import models.IngredientsConverter
import models.Product
import models.ProductDiaryEntry
import models.Recipe
import models.RecipeDiaryEntry
import utils.date.LocalDateConverter
import utils.date.LocalDateTimeConverter

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