package presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme
import utils.noRippleClickable

@Composable
fun CalendarItem(
    modifier: Modifier = Modifier,
    dayOfWeek: String,
    dayOfMonth: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val contentColor by animateColorAsState(
        targetValue = if (selected) FitnessAppTheme.colors.onPrimary else FitnessAppTheme.colors.contentSecondary,
        animationSpec = tween(durationMillis = 100, easing = LinearEasing)
    )

    val topBackgroundColor by animateColorAsState(
        targetValue = if (selected) FitnessAppTheme.colors.primary else FitnessAppTheme.colors.backgroundSecondary,
        animationSpec = tween(durationMillis = 100, easing = LinearEasing)
    )

    val bottomBackgroundColor by animateColorAsState(
        targetValue = if (selected) FitnessAppTheme.colors.secondary else FitnessAppTheme.colors.backgroundTertiary,
        animationSpec = tween(durationMillis = 100, easing = LinearEasing)
    )

    Column(
        modifier = modifier
            .width(44.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = topBackgroundColor)
            .noRippleClickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = dayOfWeek,
            style = FitnessAppTheme.typography.bodyExtraSmall,
            color = contentColor,
            modifier = Modifier.padding(vertical = 4.dp),
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = dayOfMonth,
            style = if (selected) FitnessAppTheme.typography.labelExtraLarge else FitnessAppTheme.typography.bodyExtraLarge,
            color = contentColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = bottomBackgroundColor,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                )
                .padding(top = 4.dp, bottom = 6.dp)
        )
    }
}