package presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.days
import com.gmail.bogumilmecel2.ui.composeResources.you_have_logged_for
import components.FitnessAppCard
import components.VerticalSpacer
import org.jetbrains.compose.resources.stringResource
import theme.FitnessAppTheme

@Composable
fun LogStreakSection(
    modifier: Modifier = Modifier,
    logStreak: Int,
) {
    FitnessAppCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(Res.string.you_have_logged_for),
                style = FitnessAppTheme.typography.labelLarge,
                color = FitnessAppTheme.colors.contentPrimary,
            )

            VerticalSpacer(size = 4.dp)

            Text(
                text = stringResource(Res.string.days, logStreak),
                style = FitnessAppTheme.typography.bodyMedium,
                color = FitnessAppTheme.colors.contentPrimary,
            )
        }
    }
}