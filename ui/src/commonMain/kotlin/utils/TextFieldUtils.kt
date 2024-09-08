package utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTransformationWithVisibility(passwordVisible: Boolean) = if (passwordVisible) {
    VisualTransformation.None
} else {
    PasswordVisualTransformation()
}

@Composable
fun PasswordVisibilityIcon(passwordVisible: Boolean) = if (passwordVisible) {
    Icons.Default.Visibility
} else {
    Icons.Default.VisibilityOff
}