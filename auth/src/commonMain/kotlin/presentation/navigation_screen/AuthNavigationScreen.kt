package presentation.navigation_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import cafe.adriel.voyager.core.screen.Screen
import components.FitnessAppButton
import components.IconVector
import utils.getDefaultRootModifier

class AuthNavigationScreen: Screen {
    @Composable
    override fun Content() {
        Column(
            modifier = getDefaultRootModifier(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FitnessAppButton(
                onClick = {},
                text = "",
                startIcon = IconVector.Email,
            )
        }
    }
}