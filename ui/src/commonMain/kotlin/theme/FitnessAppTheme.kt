package theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalTypography = staticCompositionLocalOf { FitnessAppTypography() }
internal val LocalColors = staticCompositionLocalOf { lightFitnessAppColorSchema() }
internal val LocalShapes = staticCompositionLocalOf { Shapes() }

val LocalRippleColor = staticCompositionLocalOf { lightFitnessAppColorSchema().contentPrimary }

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
    val colors = remember {
        if (darkTheme) darkFitnessAppColorSchema()
        else lightFitnessAppColorSchema()
    }

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides FitnessAppTypography().applyFontFamily(),
        LocalShapes provides Shapes(),
        LocalRippleTheme provides FitnessAppRippleTheme
    ) {
        content()
    }
}