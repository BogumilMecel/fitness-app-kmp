package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.components.LogStreakSection
import presentation.utils.getDefaultRootModifier

@Composable
fun SummaryTab(model: SummaryScreenModel) {
    val logStreak by model.logStreak.collectAsState()

    Column(getDefaultRootModifier()) {
        LogStreakSection(
            logStreak = logStreak,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )
    }
}