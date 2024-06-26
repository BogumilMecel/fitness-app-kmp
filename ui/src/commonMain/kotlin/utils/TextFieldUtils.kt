package utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import components.IconVector

@Composable
fun PasswordTransformationWithVisibility(passwordVisible: Boolean) = if (passwordVisible) {
    VisualTransformation.None
} else {
    PasswordVisualTransformation()
}

@Composable
fun PasswordVisibilityIcon(passwordVisible: Boolean) = if (passwordVisible) {
    IconVector.Visibility
} else {
    IconVector.VisibilityOff
}