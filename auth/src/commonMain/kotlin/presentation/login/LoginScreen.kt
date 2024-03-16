package presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.gmail.bogumilmecel2.ui.SharedRes
import components.FitnessAppButton
import components.FitnessAppClickableText
import components.FitnessAppTextField
import components.FitnessAppTopBar
import components.HorizontalSpacer
import components.IconVector
import dev.icerock.moko.resources.compose.stringResource
import theme.FitnessAppTheme
import utils.ModelLayout

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        ModelLayout<LoginScreenModel> {
            val state by state.collectAsState()

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                FitnessAppTopBar(title = stringResource(SharedRes.strings.login))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    FitnessAppTextField(
                        value = state.email,
                        label = stringResource(SharedRes.strings.email_address),
                        onValueChange = ::onEmailChanged,
                        leadingIcon = IconVector.Email
                    )

                    HorizontalSpacer()

                    FitnessAppTextField(
                        value = state.password,
                        label = stringResource(resource = SharedRes.strings.password),
                        onValueChange = ::onPasswordChanged,
                        leadingIcon = IconVector.Password
                    )

                    HorizontalSpacer(size = 24.dp)

                    FitnessAppButton(
                        onClick = ::onLoginButtonClicked,
                        startIcon = IconVector.Login,
                        text = stringResource(SharedRes.strings.sign_in)
                    )

                    HorizontalSpacer()

                    FitnessAppClickableText(
                        text = stringResource(SharedRes.strings.forgot_password),
                        style = FitnessAppTheme.typography.labelSmall,
                        color = FitnessAppTheme.colors.contentSecondary,
                        onClick = ::onForgotPasswordClicked
                    )
                }

                FitnessAppClickableText(
                    modifier = Modifier.padding(bottom = 64.dp),
                    text = stringResource(SharedRes.strings.i_don_t_have_an_account_register),
                    onClick = ::onRegisterButtonClicked
                )
            }
        }
    }
}