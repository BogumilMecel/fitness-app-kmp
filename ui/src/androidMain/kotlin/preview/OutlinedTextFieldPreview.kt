package preview

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.AppOutlinedTextField
import theme.FitnessAppTheme

@Composable
@Preview(showBackground = true)
private fun OutlinedTextFieldWithTextPreviewLight() {
    OutlinedTextFieldPreview(
        text = "Sample text",
        darkTheme = false
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun OutlinedTextFieldWithTextPreviewDark() {
    OutlinedTextFieldPreview(
        text = "Sample text",
        darkTheme = true
    )
}

@Composable
@Preview(showBackground = true)
private fun OutlinedTextFieldWithoutTextPreviewLight() {
    OutlinedTextFieldPreview(
        text = "",
        darkTheme = false
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun OutlinedTextFieldWithoutTextPreviewDark() {
    OutlinedTextFieldPreview(
        text = "",
        darkTheme = true
    )
}

@Composable
@Preview(showBackground = true)
private fun OutlinedTextFieldWithErrorAndTextPreviewLight() {
    OutlinedTextFieldPreview(
        text = "Sample text",
        isError = true,
        darkTheme = false
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun OutlinedTextFieldWithErrorAndTextPreviewDark() {
    OutlinedTextFieldPreview(
        text = "Sample text",
        isError = true,
        darkTheme = true
    )
}

@Composable
@Preview(showBackground = true)
private fun OutlinedTextFieldWithErrorWithoutTextPreviewLight() {
    OutlinedTextFieldPreview(
        text = "",
        isError = true,
        darkTheme = false
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun OutlinedTextFieldWithErrorWithoutTextPreviewDark() {
    OutlinedTextFieldPreview(
        text = "",
        isError = true,
        darkTheme = true
    )
}

@Composable
private fun OutlinedTextFieldPreview(
    text: String = "",
    label: String = "Label",
    isError: Boolean = false,
    darkTheme: Boolean,
) {
    FitnessAppTheme(darkTheme = darkTheme) {
        AppOutlinedTextField(
            text = text,
            label = label,
            onValueChange = {},
            isError = isError,
            modifier = Modifier.padding(16.dp)
        )
    }
}
