package utils.date

import kotlinx.datetime.DayOfWeek
import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarIdentifierGregorian

actual fun getSystemFirstDayOfWeek(): DayOfWeek {
    val calendar = NSCalendar.calendarWithIdentifier(NSCalendarIdentifierGregorian)!!
    return when (calendar.firstWeekday.toInt()) {
        1 -> DayOfWeek.SUNDAY
        2 -> DayOfWeek.MONDAY
        3 -> DayOfWeek.TUESDAY
        4 -> DayOfWeek.WEDNESDAY
        5 -> DayOfWeek.THURSDAY
        6 -> DayOfWeek.FRIDAY
        7 -> DayOfWeek.SATURDAY
        else -> DayOfWeek.MONDAY
    }
}