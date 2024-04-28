package components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun FitnessAppTextField(
    modifier: Modifier = Modifier,
    textFieldData: TextFieldData,
    onValueChange: (String) -> Unit,
    onErrorCleared: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: Icon? = null,
    label: String
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(key1 = isFocused) {
        if (isFocused) {
            onErrorCleared?.invoke()
        }
    }

    val isError = textFieldData.error != null
    val focusedColor by animateColorAsState(
        targetValue = if (isError) FitnessAppTheme.colors.error else FitnessAppTheme.colors.contentPrimary
    )
    val unfocusedColor by animateColorAsState(
        targetValue = if (isError) FitnessAppTheme.colors.error else FitnessAppTheme.colors.contentSecondary
    )
    val labelColor by animateColorAsState(
        targetValue = if (isError && textFieldData.text.isNotEmpty()) {
            FitnessAppTheme.colors.error
        } else {
            FitnessAppTheme.colors.contentSecondary
        }
    )
    val iconColor by animateColorAsState(
        targetValue = if (isFocused) {
            FitnessAppTheme.colors.contentPrimary
        } else {
            FitnessAppTheme.colors.contentSecondary
        }
    )

    Column(
        modifier = Modifier.animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        OutlinedTextField(
            value = textFieldData.text,
            onValueChange = {
                onErrorCleared?.invoke()
                onValueChange(it)
            },
            modifier = modifier.fillMaxWidth(),
            textStyle = FitnessAppTheme.typography.bodyLarge,
            label = { Text(text = label) },
            leadingIcon = {
                leadingIcon?.let {
                    FitnessAppIcon(
                        icon = leadingIcon,
                        tint = iconColor
                    )
                }
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = isFocused,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    FitnessAppIcon(
                        icon = IconVector.Close,
                        modifier = Modifier.clickable {
                            onValueChange("")
                        },
                        tint = iconColor
                    )
                }
            },
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = FitnessAppTheme.colors.contentPrimary,
                cursorColor = FitnessAppTheme.colors.contentPrimary,
                focusedBorderColor = focusedColor,
                unfocusedBorderColor = unfocusedColor,
                focusedLabelColor = focusedColor,
                unfocusedLabelColor = labelColor,
            ),
            interactionSource = interactionSource,
        )

        textFieldData.error?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FitnessAppIcon(
                    icon = IconVector.InfoOutlined,
                    tint = FitnessAppTheme.colors.error
                )

                Text(
                    text = it,
                    style = FitnessAppTheme.typography.bodySmall,
                    color = FitnessAppTheme.colors.error
                )
            }
        }
    }
}

data class TextFieldData(
    val text: String = "",
    val error: String? = null
)