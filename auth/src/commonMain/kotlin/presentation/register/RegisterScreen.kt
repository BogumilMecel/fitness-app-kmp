package presentation.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.email_address
import com.gmail.bogumilmecel2.ui.composeResources.password
import com.gmail.bogumilmecel2.ui.composeResources.register_header
import com.gmail.bogumilmecel2.ui.composeResources.sign_up
import com.gmail.bogumilmecel2.ui.composeResources.username
import components.FitnessAppButton
import components.FitnessAppTextField
import components.FitnessAppTopBar
import components.VerticalSpacer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import utils.TestTags

@Composable
fun RegisterScreen(viewModel: RegisterScreenModel = koinViewModel()) = with(viewModel) {
    val email by email.collectAsState()
    val username by username.collectAsState()
    val password by password.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        FitnessAppTopBar(
            title = stringResource(Res.string.register_header),
            onBackPressed = ::onBackPressed
        )

        VerticalSpacer(128.dp)

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
        ) {
            FitnessAppTextField(
                textFieldData = email,
                label = stringResource(Res.string.email_address),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = Icons.Default.Email,
                testTag = TestTags.EMAIL
            )

            VerticalSpacer()

            FitnessAppTextField(
                textFieldData = username,
                label = stringResource(resource = Res.string.username),
                leadingIcon = Icons.Default.AccountCircle,
                testTag = TestTags.USERNAME
            )

            VerticalSpacer()

            FitnessAppTextField(
                textFieldData = password,
                label = stringResource(resource = Res.string.password),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = Icons.Default.Password,
                testTag = TestTags.PASSWORD
            )

            VerticalSpacer(24.dp)

            FitnessAppButton(
                onClick = ::onRegisterButtonClicked,
                text = stringResource(resource = Res.string.sign_up),
            )
        }
    }
}