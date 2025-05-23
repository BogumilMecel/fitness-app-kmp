package components

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.FitnessAppTheme
import utils.defaultRoundedCornerShape

@Composable
fun FitnessAppCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors().copy(containerColor = FitnessAppTheme.colors.surface),
        shape = defaultRoundedCornerShape,
        modifier = modifier
    ) {
        content()
    }
}