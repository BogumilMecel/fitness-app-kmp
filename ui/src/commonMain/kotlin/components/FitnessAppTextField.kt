package components

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
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme
import utils.ClickableContent

@Composable
fun FitnessAppTextField(
    modifier: Modifier = Modifier,
    textFieldData: TextFieldData,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: ImageVector? = null,
    trailingIcon: ClickableContent.Icon? = null,
    label: String,
    maxLines: Int = 1,
    testTag: String? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(key1 = isFocused) {
        if (isFocused) {
            textFieldData.onErrorCleared()
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
        modifier = Modifier.animateContentSize().height(IntrinsicSize.Max),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        OutlinedTextField(
            value = textFieldData.text,
            onValueChange = {
                textFieldData.onErrorCleared()
                textFieldData.onValueChange(it)
            },
            modifier = modifier
                .fillMaxWidth()
                .then(other = testTag?.let { Modifier.testTag(it) } ?: Modifier),
            textStyle = FitnessAppTheme.typography.bodyLarge,
            label = { Text(text = label) },
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
            } else null,
            trailingIcon = {
                Row(
                    modifier = Modifier.animateContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    trailingIcon?.let {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = null,
                            tint = iconColor,
                            modifier = Modifier.clickable(onClick = it.onClick)
                        )
                    }

                    AnimatedVisibility(
                        visible = isFocused && textFieldData.text.isNotEmpty(),
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = iconColor,
                            modifier = Modifier
                                .padding(end = if (trailingIcon == null) 0.dp else 12.dp)
                                .clickable {
                                    textFieldData.onErrorCleared()
                                    textFieldData.onValueChange("")
                                }
                        )
                    }
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
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
        )

        textFieldData.error?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = FitnessAppTheme.colors.error,
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

@Composable
fun FitnessAppTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    onErrorCleared: () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: ImageVector? = null,
    trailingIcon: ClickableContent.Icon? = null,
    label: String,
    maxLines: Int = 1,
    testTag: String? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(key1 = isFocused) {
        if (isFocused) {
            onErrorCleared()
        }
    }

    val isError = error != null
    val focusedColor by animateColorAsState(
        targetValue = if (isError) FitnessAppTheme.colors.error else FitnessAppTheme.colors.contentPrimary
    )
    val unfocusedColor by animateColorAsState(
        targetValue = if (isError) FitnessAppTheme.colors.error else FitnessAppTheme.colors.contentSecondary
    )
    val labelColor by animateColorAsState(
        targetValue = if (isError && text.isNotEmpty()) {
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
        modifier = Modifier.animateContentSize().height(IntrinsicSize.Max),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                onErrorCleared()
                onValueChange(it)
            },
            modifier = modifier
                .fillMaxWidth()
                .then(other = testTag?.let { Modifier.testTag(it) } ?: Modifier),
            textStyle = FitnessAppTheme.typography.bodyLarge,
            label = { Text(text = label) },
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
            } else null,
            trailingIcon = {
                Row(
                    modifier = Modifier.animateContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    trailingIcon?.let {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = null,
                            tint = iconColor,
                            modifier = Modifier.clickable(onClick = it.onClick)
                        )
                    }

                    AnimatedVisibility(
                        visible = isFocused && text.isNotEmpty(),
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = iconColor,
                            modifier = Modifier
                                .padding(end = if (trailingIcon == null) 0.dp else 12.dp)
                                .clickable {
                                    onErrorCleared()
                                    onValueChange("")
                                }
                        )
                    }
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
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
        )

        error?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = FitnessAppTheme.colors.error,
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
    val onValueChange: (String) -> Unit = {},
    val error: String? = null,
    val onErrorCleared: () -> Unit = {},
)