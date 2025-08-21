package utils.date

import kotlinx.datetime.DayOfWeek
import java.time.temporal.WeekFields
import java.util.Locale

actual fun getSystemFirstDayOfWeek(): DayOfWeek {
    val javaDay = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    return DayOfWeek.valueOf(javaDay.name)
}