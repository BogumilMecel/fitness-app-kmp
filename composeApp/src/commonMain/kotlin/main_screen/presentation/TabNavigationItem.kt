package main_screen.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import navigation.presentation.Route
import org.jetbrains.compose.resources.stringResource
import theme.FitnessAppTheme

@Composable
fun RowScope.TabNavigationItem(
    bottomNavigation: Route.BottomNavigation,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val animationSpec = tween<Color>(
        durationMillis = 200,
        easing = LinearEasing
    )

    val selectedColor by animateColorAsState(
        targetValue = FitnessAppTheme.colors.primary,
        label = "selected bottom navigation item color",
        animationSpec = animationSpec
    )

    val notSelectedColor by animateColorAsState(
        targetValue = FitnessAppTheme.colors.contentTertiary,
        label = "not selected bottom navigation item color",
        animationSpec = animationSpec
    )

    this@TabNavigationItem.NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        label = { Text(text = stringResource(bottomNavigation.titleRes)) },
        icon = {
            Icon(
                imageVector = bottomNavigation.icon,
                contentDescription = null,
            )
        },
        colors = NavigationBarItemDefaults.colors().copy(
            selectedIconColor = selectedColor,
            selectedTextColor = selectedColor,
            selectedIndicatorColor = FitnessAppTheme.colors.primaryBackground,
            unselectedIconColor = notSelectedColor,
            unselectedTextColor = notSelectedColor,
        )
    )
}