package main_screen.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.bottom_nav_account
import com.gmail.bogumilmecel2.ui.composeResources.bottom_nav_diary
import com.gmail.bogumilmecel2.ui.composeResources.bottom_nav_summary
import com.gmail.bogumilmecel2.ui.composeResources.bottom_nav_training
import org.jetbrains.compose.resources.stringResource
import presentation.AccountScreenModel
import presentation.AccountTab
import presentation.DiaryScreenModel
import presentation.DiaryTab
import presentation.ModelLayout
import presentation.SummaryScreenModel
import presentation.SummaryTab
import presentation.TrainingScreenModel
import presentation.TrainingTab

object Summary : Tab {

    @Composable
    override fun Content() {
        ModelLayout<SummaryScreenModel> {
            SummaryTab(model = this)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(Res.string.bottom_nav_summary)
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
        ModelLayout<DiaryScreenModel> {
            DiaryTab(model = this)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(Res.string.bottom_nav_diary)
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
        ModelLayout<TrainingScreenModel> {
            TrainingTab(model = this)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(Res.string.bottom_nav_training)
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
        ModelLayout<AccountScreenModel> {
            AccountTab(model = this)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(Res.string.bottom_nav_account)
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