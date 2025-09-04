package presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.TextField
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import theme.FitnessAppTheme

@Composable
fun TextQuestion(
    text: String,
    focusRequester: FocusRequester,
    unitResId: StringResource?,
    onTextEntered: (String) -> Unit,
    tag: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            text = text,
            label = "",
            onValueChange = {
                onTextEntered(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .width(120.dp)
                .focusRequester(focusRequester),
            textAlign = TextAlign.Center,
            maxLines = 1
        )

        unitResId?.let {
            Text(
                text = stringResource(it),
                style = FitnessAppTheme.typography.bodyLarge,
                color = FitnessAppTheme.colors.contentPrimary,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
} 