package di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.FitnessAppDatabase

fun getAndroidSharedModule(context: Context) = createSharedNativeModule(
    fitnessAppDatabase = {
        Room.databaseBuilder<FitnessAppDatabase>(
            context = context,
            name = context.getDatabasePath(FitnessAppDatabase.DB_NAME).absolutePath
        )
            .setDriver(BundledSQLiteDriver())
            .build()
    },
)