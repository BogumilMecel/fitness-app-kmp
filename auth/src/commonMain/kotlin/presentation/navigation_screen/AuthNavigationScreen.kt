package presentation.navigation_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import components.Divider
import components.FitnessAppButton
import components.FitnessAppButtonStyle
import components.FitnessAppTopBar
import components.HorizontalSpacer
import components.IconPainter
import components.IconVector
import theme.FitnessAppTheme
import utils.ModelLayout

class AuthNavigationScreen : Screen {
    @Composable
    override fun Content() {
        ModelLayout<AuthNavigationModel> {
            FitnessAppTopBar(title = "Welcome to App Name")

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sign In",
                    color = FitnessAppTheme.colors.contentPrimary,
                    style = FitnessAppTheme.typography.labelLarge
                )

                HorizontalSpacer()

                FitnessAppButton(
                    text = "With email",
                    style = FitnessAppButtonStyle.Content,
                    startIcon = IconVector.Email,
                    onClick = {

                    }
                )

                HorizontalSpacer()

                FitnessAppButton(
                    text = "With google",
                    style = FitnessAppButtonStyle.Content,
                    startIcon = IconPainter.Google,
                    onClick = {

                    }
                )

                Divider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = "or"
                )

                FitnessAppButton(
                    text = "Sign up with email",
                    style = FitnessAppButtonStyle.Content,
                    startIcon = IconVector.Register,
                    onClick = {

                    }
                )

                HorizontalSpacer()
            }
        }
    }
}