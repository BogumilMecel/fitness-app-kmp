package components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.FitnessAppTheme

@Composable
fun FitnessAppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: Icon? = null,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = FitnessAppTheme.typography.bodyLarge,
        label = { Text(text = label) },
        leadingIcon = {
            leadingIcon?.let {
                FitnessAppIcon(icon = leadingIcon)
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = FitnessAppTheme.colors.contentPrimary,
            cursorColor = FitnessAppTheme.colors.contentPrimary,
            focusedBorderColor = FitnessAppTheme.colors.contentPrimary,
            unfocusedBorderColor = FitnessAppTheme.colors.contentSecondary,
            focusedLabelColor = FitnessAppTheme.colors.contentPrimary,
            unfocusedLabelColor = FitnessAppTheme.colors.contentSecondary,
        )
    )
}