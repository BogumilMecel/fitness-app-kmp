package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun TopBarLarge(
    title: String,
    onBackPressed: () -> Unit
) {
    Column {
        IconButton(
            onClick = onBackPressed,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }

        Text(
            text = title,
            style = FitnessAppTheme.typography.headlineMedium,
        )
    }
}