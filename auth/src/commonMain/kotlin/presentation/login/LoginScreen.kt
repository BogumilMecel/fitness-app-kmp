package presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.email_address
import com.gmail.bogumilmecel2.ui.composeResources.forgot_password
import com.gmail.bogumilmecel2.ui.composeResources.login
import com.gmail.bogumilmecel2.ui.composeResources.password
import com.gmail.bogumilmecel2.ui.composeResources.sign_in
import components.FitnessAppButton
import components.FitnessAppClickableText
import components.FitnessAppTextField
import components.FitnessAppTopBar
import components.HorizontalSpacer
import org.jetbrains.compose.resources.stringResource
import presentation.ModelLayout
import theme.FitnessAppTheme
import utils.ClickableContent
import utils.PasswordTransformationWithVisibility
import utils.PasswordVisibilityIcon
import utils.TestTags

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        ModelLayout<LoginScreenModel> {
            val email by email.collectAsState()
            val password by password.collectAsState()
            val passwordVisible by passwordVisible.collectAsState()
            val buttonEnabled by buttonEnabled.collectAsState()

            Box(modifier = Modifier.fillMaxSize()) {
                FitnessAppTopBar(
                    title = stringResource(Res.string.login),
                    onBackPressed = ::onBackPressed
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp),
                ) {
                    FitnessAppTextField(
                        textFieldData = email,
                        label = stringResource(Res.string.email_address),
                        leadingIcon = Icons.Default.Email,
                        testTag = TestTags.EMAIL,
                    )

                    HorizontalSpacer()

                    FitnessAppTextField(
                        textFieldData = password,
                        label = stringResource(resource = Res.string.password),
                        visualTransformation = PasswordTransformationWithVisibility(
                            passwordVisible = passwordVisible
                        ),
                        leadingIcon = Icons.Default.Password,
                        trailingIcon = ClickableContent.Icon(
                            icon = PasswordVisibilityIcon(passwordVisible = passwordVisible),
                            onClick = ::onShowPasswordClicked
                        ),
                        testTag = TestTags.PASSWORD,
                    )

                    HorizontalSpacer(size = 24.dp)

                    FitnessAppButton(
                        onClick = ::onLoginButtonClicked,
                        startIcon = Icons.AutoMirrored.Filled.Login,
                        enabled = buttonEnabled,
                        text = stringResource(Res.string.sign_in)
                    )

                    HorizontalSpacer()

                    FitnessAppClickableText(
                        text = stringResource(Res.string.forgot_password),
                        style = FitnessAppTheme.typography.labelSmall,
                        color = FitnessAppTheme.colors.contentSecondary,
                        onClick = ::onForgotPasswordClicked
                    )
                }
            }
        }
    }
}