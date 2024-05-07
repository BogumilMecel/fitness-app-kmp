package presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gmail.bogumilmecel2.ui.SharedRes
import components.FitnessAppCard
import components.VerticalSpacer
import components.HorizontalSpacer
import dev.icerock.moko.resources.compose.stringResource
import theme.FitnessAppTheme

object SummaryScreen : Tab {
    @Composable
    override fun Content() {
        ModelLayout<SummaryScreenModel> {
            val streakText by streakText.collectAsState()
            val caloriesData by caloriesData.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                HorizontalSpacer(12.dp)

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

                            HorizontalSpacer(4.dp)

                            Text(
                                text = it,
                                style = FitnessAppTheme.typography.bodyMedium,
                                color = FitnessAppTheme.colors.contentPrimary
                            )
                        }
                    }

                    VerticalSpacer(12.dp)
                }

                caloriesData?.let {
                    FitnessAppCard(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = stringResource(resource = SharedRes.strings.calories),
                                    style = FitnessAppTheme.typography.labelLarge,
                                    color = FitnessAppTheme.colors.contentPrimary
                                )


                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Text(
                                        text = "${it.currentCalories}",
                                        style = FitnessAppTheme.typography.labelMedium,
                                        color = FitnessAppTheme.colors.contentPrimary
                                    )

                                    Text(
                                        text = "/",
                                        style = FitnessAppTheme.typography.labelMedium,
                                        color = FitnessAppTheme.colors.contentPrimary
                                    )

                                    Text(
                                        text = "${it.wantedCalories} Kcal",
                                        style = FitnessAppTheme.typography.bodyMedium,
                                        color = FitnessAppTheme.colors.contentPrimary
                                    )
                                }
                            }

                            HorizontalSpacer(64.dp)

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = it.progressText,
                                    style = FitnessAppTheme.typography.bodyMedium,
                                    color = FitnessAppTheme.colors.contentSecondary
                                )

                                LinearProgressIndicator(
                                    progress = it.progress,
                                    color = FitnessAppTheme.colors.primary
                                )
                            }
                        }
                    }
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