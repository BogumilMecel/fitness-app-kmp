package presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.email_address
import com.gmail.bogumilmecel2.ui.composeResources.forgot_password
import com.gmail.bogumilmecel2.ui.composeResources.login
import com.gmail.bogumilmecel2.ui.composeResources.password
import com.gmail.bogumilmecel2.ui.composeResources.sign_in
import components.AppOutlinedTextField
import components.AppTopBar
import components.FitnessAppButton
import components.FitnessAppClickableText
import components.VerticalSpacer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.FitnessAppTheme
import utils.PasswordTransformationWithVisibility
import utils.PasswordVisibilityIcon
import utils.TestTags
import utils.noRippleClickable

@Composable
fun LoginScreen(viewModel: LoginScreenModel = koinViewModel()) = with(viewModel) {
    val email by email.collectAsState()
    val password by password.collectAsState()
    val passwordVisible by passwordVisible.collectAsState()
    val buttonEnabled by buttonEnabled.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = stringResource(Res.string.login),
            onBackPressed = ::onBackPressed
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
        ) {
            AppOutlinedTextField(
                text = email.text,
                onValueChange = email.onValueChange,
                label = stringResource(Res.string.email_address),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                    )
                },
                testTag = TestTags.EMAIL,
            )

            VerticalSpacer()

            AppOutlinedTextField(
                text = password.text,
                onValueChange = password.onValueChange,
                label = stringResource(resource = Res.string.password),
                visualTransformation = PasswordTransformationWithVisibility(
                    passwordVisible = passwordVisible
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Password,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = PasswordVisibilityIcon(passwordVisible = passwordVisible),
                        contentDescription = null,
                        modifier = Modifier.noRippleClickable(onClick = ::onShowPasswordClicked)
                    )
                },
                testTag = TestTags.PASSWORD,
            )

            VerticalSpacer(size = 24.dp)

            FitnessAppButton(
                onClick = ::onLoginButtonClicked,
                startIcon = Icons.AutoMirrored.Filled.Login,
                enabled = buttonEnabled,
                text = stringResource(Res.string.sign_in)
            )

            VerticalSpacer()

            FitnessAppClickableText(
                text = stringResource(Res.string.forgot_password),
                style = FitnessAppTheme.typography.labelSmall,
                color = FitnessAppTheme.colors.contentSecondary,
                onClick = ::onForgotPasswordClicked
            )
        }
    }
}