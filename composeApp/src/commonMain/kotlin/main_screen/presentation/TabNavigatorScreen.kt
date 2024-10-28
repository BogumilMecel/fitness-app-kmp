package main_screen.presentation

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import presentation.ModelLayout
import theme.FitnessAppTheme

class TabNavigatorScreen : Screen {
    @Composable
    override fun Content() {
        ModelLayout<TabNavigatorModel> {
            val tabNavigationEnabled by this.tabNavigationEnabled.collectAsState()
            var tabNavigationVisible by remember { mutableStateOf(true) }

            val diaryTab = remember { DiaryTab { tabNavigationVisible = it } }

            TabNavigator(tab = Summary) {
                Scaffold(
                    content = {
                        CurrentTab()
                    },
                    bottomBar = {
                        if (tabNavigationVisible) {
                            BottomNavigation(
                                backgroundColor = FitnessAppTheme.colors.backgroundSecondary,
                                modifier = Modifier.height(60.dp)
                            ) {
                                if (tabNavigationEnabled) {
                                    TabNavigationItem(tab = Summary)
                                    TabNavigationItem(tab = diaryTab)
                                    TabNavigationItem(tab = TrainingTab)
                                    TabNavigationItem(tab = AccountTab)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}