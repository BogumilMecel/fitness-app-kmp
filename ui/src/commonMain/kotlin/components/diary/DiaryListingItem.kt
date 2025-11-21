package components.diary

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.carbs
import com.gmail.bogumilmecel2.ui.composeResources.fat
import com.gmail.bogumilmecel2.ui.composeResources.kcal
import com.gmail.bogumilmecel2.ui.composeResources.protein
import components.Divider
import org.jetbrains.compose.resources.stringResource
import theme.FitnessAppTheme

@Composable
fun DiaryListingItem(
    modifier: Modifier = Modifier,
    params: DiaryListingItemParams,
) {
    DiaryListingItem(
        modifier = modifier,
        productName = params.productName,
        servingSize = params.servingSize,
        calories = params.calories,
        carbohydrates = params.carbohydrates,
        protein = params.protein,
        fat = params.fat,
        showCompact = params.showCompact,
        onClick = params.onClick,
    )
}

@Composable
fun DiaryListingItem(
    modifier: Modifier = Modifier,
    productName: String,
    servingSize: String,
    calories: Int,
    carbohydrates: Double,
    protein: Double,
    fat: Double,
    showCompact: Boolean,
    onClick: () -> Unit
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row {
                Column {
                    Text(
                        text = productName,
                        style = FitnessAppTheme.typography.labelLarge,
                        color = FitnessAppTheme.colors.contentPrimary,
                    )

                    Text(
                        text = servingSize,
                        style = FitnessAppTheme.typography.labelSmall,
                        color = FitnessAppTheme.colors.contentPrimary,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$calories ${stringResource(Res.string.kcal)}",
                        style = FitnessAppTheme.typography.labelLarge,
                        color = FitnessAppTheme.colors.contentPrimary,
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(width = 2.dp, color = FitnessAppTheme.colors.primary, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = FitnessAppTheme.colors.primary,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }

            if (!showCompact) {
                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                    DiaryNutritionItem(
                        name = stringResource(Res.string.carbs),
                        value = carbohydrates,
                        color = FitnessAppTheme.colors.carbohydrates,
                    )

                    DiaryNutritionItem(
                        name = stringResource(Res.string.protein),
                        value = protein,
                        color = FitnessAppTheme.colors.protein,
                    )

                    DiaryNutritionItem(
                        name = stringResource(Res.string.fat),
                        value = fat,
                        color = FitnessAppTheme.colors.fat,
                    )
                }
            }
        }
        Divider(modifier = Modifier.padding(horizontal = 16.dp))
    }
}

data class DiaryListingItemParams(
    val productName: String,
    val servingSize: String,
    val calories: Int,
    val carbohydrates: Double,
    val protein: Double,
    val fat: Double,
    val showCompact: Boolean,
    val onClick: () -> Unit
)