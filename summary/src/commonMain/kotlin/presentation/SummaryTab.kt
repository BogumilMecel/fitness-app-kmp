package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import presentation.components.LogStreakSection
import presentation.utils.getDefaultRootModifier

@Composable
fun SummaryScreen(viewModel: SummaryScreenModel = koinViewModel()) = with(viewModel) {
    val logStreak by logStreak.collectAsState()

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