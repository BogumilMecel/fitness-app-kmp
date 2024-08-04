package utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

internal val defaultRoundedCornerShape: Shape
    @Composable
    get() = RoundedCornerShape(12.dp)