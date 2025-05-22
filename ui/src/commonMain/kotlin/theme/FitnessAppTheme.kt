package theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalTypography = staticCompositionLocalOf { FitnessAppTypography() }
internal val LocalColors = staticCompositionLocalOf { lightFitnessAppColorSchema() }
internal val LocalShapes = staticCompositionLocalOf { Shapes() }

object FitnessAppTheme {
    val typography: FitnessAppTypography
        @ReadOnlyComposable
        @Composable get() = LocalTypography.current

    val colors: FitnessAppColorScheme
        @ReadOnlyComposable
        @Composable get() = LocalColors.current

    val shapes: Shapes
        @ReadOnlyComposable
        @Composable get() = LocalShapes.current
}

@Composable
fun FitnessAppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkFitnessAppColorSchema() else lightFitnessAppColorSchema()
    val materialColors = if (darkTheme) darkColorScheme() else lightColorScheme()

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides FitnessAppTypography().applyFontFamily(),
        LocalShapes provides FitnessAppTheme.shapes,
    ) {
        MaterialTheme(colorScheme = materialColors, content = content)
    }
}