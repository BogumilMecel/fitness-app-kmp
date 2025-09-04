package navigation.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.bottom_nav_account
import com.gmail.bogumilmecel2.ui.composeResources.bottom_nav_diary
import com.gmail.bogumilmecel2.ui.composeResources.bottom_nav_summary
import com.gmail.bogumilmecel2.ui.composeResources.bottom_nav_training
import domain.model.SelectorItem
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import models.MealName
import models.Product
import org.jetbrains.compose.resources.StringResource

sealed interface Route {
    sealed interface BottomNavigation : Route {
        val titleRes: StringResource
        val icon: ImageVector

        @Serializable
        data object Summary : BottomNavigation {
            override val titleRes: StringResource = Res.string.bottom_nav_summary
            override val icon: ImageVector = Icons.Default.Home
        }

        @Serializable
        data object Diary : BottomNavigation {
            override val titleRes: StringResource = Res.string.bottom_nav_diary
            override val icon: ImageVector = Icons.Default.Book
        }

        @Serializable
        data object Training : BottomNavigation {
            override val titleRes: StringResource = Res.string.bottom_nav_training
            override val icon: ImageVector = Icons.Default.FitnessCenter
        }

        @Serializable
        data object Account : BottomNavigation {
            override val titleRes: StringResource = Res.string.bottom_nav_account
            override val icon: ImageVector = Icons.Default.AccountCircle
        }

        companion object {
            val entries: List<BottomNavigation> = listOf(Summary, Diary, Training, Account)
        }
    }
    @Serializable
    data object Splash : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object Register : Route

    @Serializable
    data object AuthNavigator : Route

    @Serializable
    data object Introduction : Route

    @Serializable
    data class DiarySearch(
        val date: LocalDate,
        val mealName: MealName,
    ) : Route

    @Serializable
    data object NewProduct : Route

    @Serializable
    data class AddProductDiaryEntry(
        val product: Product,
        val date: LocalDate,
        val mealName: MealName,
        val weight: Int,
    ) : Route

    @Serializable
    data class EditProductDiaryEntry(
        val productId: String,
        val productDiaryEntryId: String,
    ) : Route

    @Serializable
    data class Selector(
        val title: String,
        val items: List<SelectorItem>
    ) : Route
}