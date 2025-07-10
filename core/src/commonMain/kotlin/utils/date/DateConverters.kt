package utils.date

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.Json

class LocalDateConverter {

    @TypeConverter
    fun fromLocalDate(value: LocalDate) = Json.encodeToString(value)

    @TypeConverter
    fun toLocalDate(value: String): LocalDate = Json.decodeFromString(value)
}

class LocalDateTimeConverter {

    @TypeConverter
    fun fromLocalDate(value: LocalDateTime) = Json.encodeToString(value)

    @TypeConverter
    fun toLocalDate(value: String): LocalDateTime = Json.decodeFromString(value)
}