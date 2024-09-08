package main_screen.presentation

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import theme.FitnessAppTheme

class TabNavigatorScreen: Screen {
    @Composable
    override fun Content() {
        TabNavigator(tab = Summary) {
            Scaffold(
                content = {
                    CurrentTab()
                },
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = FitnessAppTheme.colors.backgroundSecondary,
                        modifier = Modifier.height(60.dp)
                    ) {
                        TabNavigationItem(tab = Summary)
                        TabNavigationItem(tab = DiaryTab)
                        TabNavigationItem(tab = TrainingTab)
                        TabNavigationItem(tab = AccountTab)
                    }
                }
            )
        }
    }
}