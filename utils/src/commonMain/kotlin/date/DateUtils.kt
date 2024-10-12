package date

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentDate() = Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault()).date