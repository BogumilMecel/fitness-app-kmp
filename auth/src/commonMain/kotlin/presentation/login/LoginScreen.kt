package presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import presentation.ModelLayout
import utils.ClickableContent
import utils.PasswordTransformationWithVisibility
import utils.PasswordVisibilityIcon

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        ModelLayout<LoginScreenModel> {
            val email by email.collectAsState()
            val password by password.collectAsState()
            val passwordVisible by passwordVisible.collectAsState()

            Box(modifier = Modifier.fillMaxSize()) {
                FitnessAppTopBar(
                    title = stringResource(SharedRes.strings.login),
                    onBackPressed = ::onBackPressed
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp),
//                    verticalArrangement = Arrangement.Center,
                ) {
                    FitnessAppTextField(
                        textFieldData = email,
                        label = stringResource(SharedRes.strings.email_address),
                        leadingIcon = IconVector.Email
                    )

                    HorizontalSpacer()

                    FitnessAppTextField(
                        textFieldData = password,
                        label = stringResource(resource = SharedRes.strings.password),
                        visualTransformation = PasswordTransformationWithVisibility(
                            passwordVisible = passwordVisible
                        ),
                        leadingIcon = IconVector.Password,
                        trailingIcon = ClickableContent.Icon(
                            icon = PasswordVisibilityIcon(passwordVisible = passwordVisible),
                            onClick = ::onShowPasswordClicked
                        )
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
            }
        }
    }
}