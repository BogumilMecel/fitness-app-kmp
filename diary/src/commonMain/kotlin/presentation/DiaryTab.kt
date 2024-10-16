package presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import presentation.components.CalendarItem
import compose.getScreenWidth
import date.getShortName
import presentation.utils.getDefaultRootModifier

@Composable
fun DiaryTab(model: DiaryScreenModel) {
    val selectedDate by model.selectedDate.collectAsState()

    Column(getDefaultRootModifier()) {
        LazyRow(
            state = rememberLazyListState(
                initialFirstVisibleItemIndex = model.availableDates.size / 2,
                initialFirstVisibleItemScrollOffset = with(LocalDensity.current) {
                    // get negative screen width in px - 4px for padding in 29 elements
                    // + 2 * 2px for horizontal content padding and divide it by half
                    -((getScreenWidth().roundToPx() - 4 * (model.availableDates.size) + 1) / 2)
                }
            ),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(start = 2.dp, end = 2.dp, top = 8.dp)
        ) {
            items(items = model.availableDates) { date ->
                CalendarItem(
                    dayOfWeek = date.dayOfWeek.getShortName(),
                    dayOfMonth = date.dayOfMonth.toString(),
                    selected = selectedDate == date,
                    onClick = {
                        model.onDateSelected(date)
                    }
                )
            }
        }
    }
}