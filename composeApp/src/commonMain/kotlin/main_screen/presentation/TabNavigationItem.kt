package main_screen.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import compose.getScreenWidth
import theme.FitnessAppTheme

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab

    val animationSpec = tween<Color>(
        durationMillis = 200,
        easing = LinearEasing
    )

    val selectedColor by animateColorAsState(
        targetValue = FitnessAppTheme.colors.primary,
        label = "selected bottom navigation item color",
        animationSpec = animationSpec
    )

    val dividerColor by animateColorAsState(
        targetValue = if (isSelected) FitnessAppTheme.colors.primary else FitnessAppTheme.colors.background,
        label = "bottom navigation divider color",
        animationSpec = animationSpec
    )

    val notSelectedColor by animateColorAsState(
        targetValue = FitnessAppTheme.colors.contentTertiary,
        label = "not selected bottom navigation item color",
        animationSpec = animationSpec
    )

    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Divider(
            modifier = Modifier.width((getScreenWidth() / 4)),
            color = dividerColor
        )

        this@TabNavigationItem.BottomNavigationItem(
            selected = isSelected,
            onClick = { tabNavigator.current = tab },
            label = { Text(text = tab.options.title) },
            icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = null) } },
            selectedContentColor = selectedColor,
            unselectedContentColor = notSelectedColor,
        )
    }
}