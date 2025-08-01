package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun FitnessAppTopBar(
    title: String?,
    subTitle: String? = null,
    onBackPressed: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        onBackPressed?.let {
            FitnessAppIconButton(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onClick = onBackPressed,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 12.dp)
                .padding(end = 48.dp)
                .then(
                    other = if (onBackPressed == null) {
                        Modifier.padding(start = 48.dp)
                    } else Modifier
                )
        ) {
            title?.let {
                Text(
                    text = title,
                    style = FitnessAppTheme.typography.headlineMedium,
                    color = FitnessAppTheme.colors.contentPrimary
                )
            }
            subTitle?.let {
                Text(
                    text = subTitle,
                    style = FitnessAppTheme.typography.labelSmall,
                    color = FitnessAppTheme.colors.contentSecondary
                )
            }
        }
    }
}

@Composable
fun SheetTopBarWithEndButton(
    title: String,
    endButtonText: String,
    endButtonTextColor: Color,
    onEndButtonClicked: () -> Unit,
    onBackPressed: () -> Unit
) {
    var rightTextWidth by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 16.dp)
            .fillMaxWidth(),
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp),
            onClick = onBackPressed,
        )

        Text(
            text = title,
            style = FitnessAppTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    horizontal = with(LocalDensity.current) { rightTextWidth.toDp() },
                )
        )

        Text(
            text = endButtonText,
            style = FitnessAppTheme.typography.labelExtraLarge,
            color = endButtonTextColor,
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .onGloballyPositioned { coordinates ->
                    rightTextWidth = coordinates.size.width
                }
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                ) {
                    onEndButtonClicked()
                }
        )
    }
}