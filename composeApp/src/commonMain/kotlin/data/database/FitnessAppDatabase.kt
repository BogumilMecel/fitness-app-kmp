package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.api.DiaryDao
import domain.model.NutritionValuesTypeConverter
import domain.model.Product
import domain.model.ProductDiaryEntry
import domain.model.Recipe
import domain.model.RecipeDiaryEntry

@Database(
    entities = [Product::class, Recipe::class, ProductDiaryEntry::class, RecipeDiaryEntry::class],
    version = 1,
)
@TypeConverters(value = [NutritionValuesTypeConverter::class])
abstract class FitnessAppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "fitness_app_database.db"
    }

    abstract val diaryDao: DiaryDao
}