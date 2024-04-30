package presentation.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.gmail.bogumilmecel2.ui.SharedRes
import components.FitnessAppButton
import components.FitnessAppTextField
import components.FitnessAppTopBar
import components.HorizontalSpacer
import components.IconVector
import dev.icerock.moko.resources.compose.stringResource
import presentation.ModelLayout

class RegisterScreen : Screen {

    @Composable
    override fun Content() {
        ModelLayout<RegisterScreenModel> {
            val email by email.collectAsState()
            val username by username.collectAsState()
            val password by password.collectAsState()
            val confirmPassword by confirmPassword.collectAsState()

            Box(modifier = Modifier.fillMaxSize()) {
                FitnessAppTopBar(
                    title = stringResource(resource = SharedRes.strings.register_header),
                    onBackPressed = ::onBackPressed
                )

                HorizontalSpacer(128.dp)

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp),
                ) {
                    FitnessAppTextField(
                        textFieldData = email,
                        label = stringResource(SharedRes.strings.email_address),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        leadingIcon = IconVector.Email,
//                        testTag = TestTags.EMAIL
                    )

                    HorizontalSpacer()

                    FitnessAppTextField(
                        textFieldData = username,
                        label = stringResource(resource = SharedRes.strings.username),
                        leadingIcon = IconVector.Account,
//                        testTag = TestTags.USERNAME
                    )

                    HorizontalSpacer()

                    FitnessAppTextField(
                        textFieldData = password,
                        label = stringResource(resource = SharedRes.strings.password),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = IconVector.Password,
//                        testTag = TestTags.PASSWORD
                    )

                    HorizontalSpacer()

                    FitnessAppTextField(
                        textFieldData = confirmPassword,
                        label = stringResource(resource = SharedRes.strings.confirm_your_password),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = IconVector.Password,
//                        testTag = TestTags.CONFIRM_PASSWORD
                    )

                    HorizontalSpacer(24.dp)

                    FitnessAppButton(
                        onClick = ::onRegisterButtonClicked,
                        text = stringResource(resource = SharedRes.strings.sign_up)
                    )
                }
            }
        }
    }
}