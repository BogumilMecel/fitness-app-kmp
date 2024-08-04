package components

import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.FitnessAppTheme
import utils.defaultRoundedCornerShape

@Composable
fun FitnessAppCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        backgroundColor = FitnessAppTheme.colors.surface,
        shape = defaultRoundedCornerShape,
        modifier = modifier
    ) {
        content()
    }
}