package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gmail.bogumilmecel2.ui.SharedRes
import components.FitnessAppCard
import components.VerticalSpacer
import dev.icerock.moko.resources.compose.stringResource
import theme.FitnessAppTheme

object SummaryScreen : Tab {
    @Composable
    override fun Content() {
        ModelLayout<SummaryScreenModel> {
            val streakText by streakText.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                VerticalSpacer(12.dp)

                streakText?.let {
                    FitnessAppCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = stringResource(resource = SharedRes.strings.you_have_logged_for),
                                style = FitnessAppTheme.typography.labelLarge,
                                color = FitnessAppTheme.colors.contentPrimary
                            )

                            VerticalSpacer(4.dp)

                            Text(
                                text = it,
                                style = FitnessAppTheme.typography.bodyMedium,
                                color = FitnessAppTheme.colors.contentPrimary
                            )
                        }
                    }

                    VerticalSpacer(12.dp)
                }
            }
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(SharedRes.strings.bottom_nav_summary)
            val icon = rememberVectorPainter(Icons.Default.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }
}