package main_screen.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gmail.bogumilmecel2.ui.SharedRes
import dev.icerock.moko.resources.compose.stringResource

object SummaryTab : Tab {

    @Composable
    override fun Content() {
        Text(text = "summary")
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

object DiaryTab : Tab {

    @Composable
    override fun Content() {
        Text(text = "diary")
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(SharedRes.strings.bottom_nav_diary)
            val icon = rememberVectorPainter(Icons.Default.Book)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }
}

object TrainingTab : Tab {

    @Composable
    override fun Content() {
        Text(text = "trainig")
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(SharedRes.strings.bottom_nav_training)
            val icon = rememberVectorPainter(Icons.Default.FitnessCenter)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }
}

object AccountTab : Tab {

    @Composable
    override fun Content() {
        Text(text = "Account")
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(SharedRes.strings.bottom_nav_account)
            val icon = rememberVectorPainter(Icons.Default.AccountCircle)

            return remember {
                TabOptions(
                    index = 3u,
                    title = title,
                    icon = icon
                )
            }
        }
}