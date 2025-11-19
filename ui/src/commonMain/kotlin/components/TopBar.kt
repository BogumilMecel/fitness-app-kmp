package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun AppTopBar(
    title: String?,
    subTitle: String? = null,
    onBackPressed: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        onBackPressed?.let {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = FitnessAppTheme.colors.contentPrimary,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(vertical = 8.dp)
                    .clickable(onClick = it),
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .align(Alignment.Center)
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
                    style = FitnessAppTheme.typography.labelMedium,
                    color = FitnessAppTheme.colors.contentSecondary
                )
            }
        }
    }
}