package components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun HorizontalProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float = 0F,
) {
    val progressState by animateFloatAsState(targetValue = progress / 100)

    Column(
        modifier = modifier
            .height(30.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(FitnessAppTheme.colors.contentSecondary)
            .width(6.dp),
    ) {
        Box(
            modifier = Modifier
                .weight(if ((1F - progressState) < 0F) 0f else 1 - progressState)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .weight(if (progressState < 0F) 0f else progressState)
                .fillMaxWidth()
                .background(FitnessAppTheme.colors.primary)
        )
    }
}