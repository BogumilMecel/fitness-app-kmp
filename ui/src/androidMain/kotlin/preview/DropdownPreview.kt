package preview

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.AppDropdown
import theme.FitnessAppTheme

@Composable
@Preview(showBackground = true)
private fun OutlinedTextFieldWithTextPreviewLight() {
    DropdownPreview(
        text = "Sample option",
        darkTheme = false
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun OutlinedTextFieldWithTextPreviewDark() {
    DropdownPreview(
        text = "Sample option",
        darkTheme = true
    )
}

@Composable
@Preview(showBackground = true)
private fun DropdownWithoutTextPreviewLight() {
    DropdownPreview(darkTheme = false)
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun DropdownWithoutTextPreviewDark() {
    DropdownPreview(darkTheme = true)
}

@Composable
private fun DropdownPreview(
    text: String = "",
    label: String = "Dropdown Label",
    darkTheme: Boolean,
) {
    FitnessAppTheme(darkTheme = darkTheme) {
        AppDropdown(
            text = text,
            label = label,
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}