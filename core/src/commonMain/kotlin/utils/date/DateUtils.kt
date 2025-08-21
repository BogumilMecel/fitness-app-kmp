package utils.date

import androidx.compose.runtime.Composable
import com.gmail.bogumilmecel2.core.composeResources.Res
import com.gmail.bogumilmecel2.core.composeResources.friday_short_name
import com.gmail.bogumilmecel2.core.composeResources.monday_short_name
import com.gmail.bogumilmecel2.core.composeResources.saturday_short_name
import com.gmail.bogumilmecel2.core.composeResources.sunday_short_name
import com.gmail.bogumilmecel2.core.composeResources.thursday_short_name
import com.gmail.bogumilmecel2.core.composeResources.today
import com.gmail.bogumilmecel2.core.composeResources.tuesday_short_name
import com.gmail.bogumilmecel2.core.composeResources.wednesday_short_name
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource

fun getCurrentDate() = Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault()).date

fun LocalDate.isToday() = equals(getCurrentDate())

@Composable
fun LocalDate.getDisplayValue() = when {
    isToday() -> stringResource(Res.string.today)
    else -> toString()
}

@Composable
fun DayOfWeek.getShortName() = when(this) {
    DayOfWeek.MONDAY -> stringResource(Res.string.monday_short_name)
    DayOfWeek.TUESDAY -> stringResource(Res.string.tuesday_short_name)
    DayOfWeek.WEDNESDAY -> stringResource(Res.string.wednesday_short_name)
    DayOfWeek.THURSDAY-> stringResource(Res.string.thursday_short_name)
    DayOfWeek.FRIDAY -> stringResource(Res.string.friday_short_name)
    DayOfWeek.SATURDAY -> stringResource(Res.string.saturday_short_name)
    DayOfWeek.SUNDAY -> stringResource(Res.string.sunday_short_name)
    else -> ""
}

expect fun getSystemFirstDayOfWeek(): DayOfWeek