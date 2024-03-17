package components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(
    size: Dp = 16.dp,
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = modifier.height(size))
}

@Composable
fun VerticalSpacer(
    modifier: Modifier = Modifier,
    size: Dp = 16.dp
) {
    Spacer(modifier = modifier.width(size))
}