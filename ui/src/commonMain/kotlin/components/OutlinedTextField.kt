package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme
import utils.noRippleClickable
import utils.optionalTestTag

@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = FitnessAppTheme.colors.background,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
    focusRequester: FocusRequester = remember { FocusRequester() },
    testTag: String? = null,
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged(
                onFocusChanged = {
                    isFocused = it.isFocused
                }
            )
            .optionalTestTag(testTag),
        textStyle = FitnessAppTheme.typography.bodyLarge.copy(textAlign = textAlign),
        label = {
            Text(text = label)
        },
        leadingIcon = leadingIcon,
        trailingIcon = {
            Row(
                modifier = Modifier
                    .animateContentSize()
                    .padding(end = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                trailingIcon?.let {
                    it()
                }

                AnimatedVisibility(
                    visible = isFocused && text.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .noRippleClickable {
                                onValueChange("")
                            }
                    )
                }
            }
        },
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = FitnessAppTheme.colors.contentPrimary,
            unfocusedBorderColor = FitnessAppTheme.colors.contentSecondary,
            errorBorderColor = FitnessAppTheme.colors.error,
        ).copy(
            focusedTextColor = FitnessAppTheme.colors.contentPrimary,
            unfocusedTextColor = FitnessAppTheme.colors.contentSecondary,
            cursorColor = FitnessAppTheme.colors.contentPrimary,
            focusedLabelColor = FitnessAppTheme.colors.contentPrimary,
            unfocusedLabelColor = FitnessAppTheme.colors.contentSecondary,
            errorLabelColor = FitnessAppTheme.colors.error,
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor,
            errorContainerColor = backgroundColor,
            focusedLeadingIconColor = FitnessAppTheme.colors.contentPrimary,
            unfocusedLeadingIconColor = FitnessAppTheme.colors.contentSecondary,
            focusedTrailingIconColor = FitnessAppTheme.colors.contentPrimary,
            unfocusedTrailingIconColor = FitnessAppTheme.colors.backgroundSecondary,
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        isError = isError,
    )
}

data class TextFieldData(
    val text: String = "",
    val onValueChange: (String) -> Unit = {},
    val error: String? = null,
    val onErrorCleared: () -> Unit = {},
)