package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object FitnessAppRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = FitnessAppTheme.colors.contentPrimary

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = FitnessAppTheme.colors.contentPrimary,
        lightTheme = !isSystemInDarkTheme()
    )
}