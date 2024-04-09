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
import com.gmail.bogumilmecel2.ui.SharedRes
import components.Divider
import components.FitnessAppButton
import components.FitnessAppButtonStyle
import components.FitnessAppTopBar
import components.HorizontalSpacer
import components.IconPainter
import components.IconVector
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.compose.stringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import theme.FitnessAppTheme
import presentation.ModelLayout

class AuthNavigationScreen : Screen {
    @Composable
    override fun Content() {
        ModelLayout<AuthNavigationModel> {
            FitnessAppTopBar(title = StringDesc.Resource(SharedRes.strings.welcome_to).localized())

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(SharedRes.strings.sing_in_with),
                    color = FitnessAppTheme.colors.contentPrimary,
                    style = FitnessAppTheme.typography.labelLarge
                )

                HorizontalSpacer()

                FitnessAppButton(
                    text = stringResource(SharedRes.strings.email),
                    style = FitnessAppButtonStyle.Content,
                    startIcon = IconVector.Email,
                    onClick = ::onSignInWithEmailClicked
                )

                HorizontalSpacer()

                FitnessAppButton(
                    text = stringResource(SharedRes.strings.google),
                    style = FitnessAppButtonStyle.Content,
                    startIcon = IconPainter.Google,
                    onClick = ::onSignInWithGoogleClicked
                )

                Divider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = stringResource(SharedRes.strings.or_)
                )

                FitnessAppButton(
                    text = stringResource(SharedRes.strings.sign_up_with_email),
                    style = FitnessAppButtonStyle.Content,
                    startIcon = IconVector.Register,
                    onClick = ::onSignUpWithEmailClicked
                )
            }
        }
    }
}