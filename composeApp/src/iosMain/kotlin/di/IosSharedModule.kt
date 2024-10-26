package di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.FitnessAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory

val iosSharedModule = createSharedNativeModule(
    fitnessAppDatabase = {
        Room.databaseBuilder<FitnessAppDatabase>(
            name = NSHomeDirectory() + "/${FitnessAppDatabase.DB_NAME}",
            factory = { FitnessAppDatabase::class.instantiateImpl() }
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
)