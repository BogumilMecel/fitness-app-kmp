package components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DonutChart(
    slices: List<ChartSlice>,
    modifier: Modifier = Modifier,
    strokeWidth: Dp,
    lineGap: Dp,
    lineColor: Color,
    centerText: (@Composable () -> Unit)?,
) {
    val density = LocalDensity.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val center = Offset(x = canvasWidth / 2f, y = canvasHeight / 2f)

            val strokePx = with(density) { strokeWidth.toPx() }
            val gapPx = with(density) { lineGap.toPx() }
            val lineStrokePx = with(density) { 2.dp.toPx() }

            val outerRadius = (minOf(canvasWidth, canvasHeight) / 2f)
            val arcRadius = outerRadius - strokePx / 2f

            val arcRect = Rect(
                left = center.x - arcRadius,
                top = center.y - arcRadius,
                right = center.x + arcRadius,
                bottom = center.y + arcRadius
            )

            val total = slices.sumOf { it.value.toDouble() }.toFloat().coerceAtLeast(1f)

            var startAngle = -90f
            for (slice in slices) {
                val sweep = 360f * (slice.value / total)
                drawArc(
                    color = slice.color,
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    topLeft = Offset(arcRect.left, arcRect.top),
                    size = androidx.compose.ui.geometry.Size(arcRect.width, arcRect.height),
                    style = Stroke(width = strokePx, cap = StrokeCap.Round, join = StrokeJoin.Round)
                )
                startAngle += sweep
            }

            val outerEdge = arcRadius + strokePx / 2f
            val innerEdge = arcRadius - strokePx / 2f

            val innerLineRadius = (innerEdge - gapPx).coerceAtLeast(0f)
            drawCircle(
                color = lineColor,
                radius = innerLineRadius,
                center = center,
                style = Stroke(width = lineStrokePx)
            )

            val outerLineRadius = outerEdge + gapPx
            drawCircle(
                color = lineColor,
                radius = outerLineRadius,
                center = center,
                style = Stroke(width = lineStrokePx)
            )
        }

        if (centerText != null) {
            centerText()
        }
    }
}

data class ChartSlice(
    val value: Float,
    val color: Color
)