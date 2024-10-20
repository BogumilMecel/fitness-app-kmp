package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.api.DiaryDao
import domain.model.Product

@Database(
    entities = [Product::class],
    version = 1,
)
abstract class FitnessAppDatabase : RoomDatabase() {
    abstract val diaryDao: DiaryDao
}