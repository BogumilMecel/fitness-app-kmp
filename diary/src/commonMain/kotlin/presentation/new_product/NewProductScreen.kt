package presentation.new_product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.barcode_scan
import com.gmail.bogumilmecel2.diary.composeResources.calories
import com.gmail.bogumilmecel2.diary.composeResources.carbs
import com.gmail.bogumilmecel2.diary.composeResources.fat
import com.gmail.bogumilmecel2.diary.composeResources.new_product
import com.gmail.bogumilmecel2.diary.composeResources.new_product_barcode
import com.gmail.bogumilmecel2.diary.composeResources.new_product_container_weight
import com.gmail.bogumilmecel2.diary.composeResources.new_product_in_100_grams
import com.gmail.bogumilmecel2.diary.composeResources.new_product_in_average
import com.gmail.bogumilmecel2.diary.composeResources.new_product_in_container
import com.gmail.bogumilmecel2.diary.composeResources.new_product_nutrition_values
import com.gmail.bogumilmecel2.diary.composeResources.new_product_product_name
import com.gmail.bogumilmecel2.diary.composeResources.protein
import components.FitnessAppTopBar
import components.TextField
import components.VerticalSpacer
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.utils.getDefaultRootModifier
import theme.FitnessAppTheme

@Composable
fun NewProductScreen(
    state: NewProductState,
    onEvent: (NewProductEvent) -> Unit,
) {
    Column(
        modifier = getDefaultRootModifier()
            .verticalScroll(state = rememberScrollState())
    ) {
        FitnessAppTopBar(
            title = stringResource(Res.string.new_product),
            onBackPressed = {
                onEvent(NewProductEvent.BackPressed)
            }
        )

        VerticalSpacer(size = 12.dp)

        TextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = state.productName,
            label = stringResource(Res.string.new_product_product_name),
            onValueChange = {
                onEvent(NewProductEvent.ProductNameChanged(it))
            }
        )

        VerticalSpacer(size = 12.dp)

        TextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = state.containerWeight,
            label = stringResource(Res.string.new_product_container_weight),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            onValueChange = {
                onEvent(NewProductEvent.ContainerWeightChanged(it))
            }
        )

        VerticalSpacer(size = 12.dp)

        TextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = state.barcode,
            label = stringResource(Res.string.new_product_barcode),
            trailingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.barcode_scan),
                    contentDescription = null,
                    tint = FitnessAppTheme.colors.contentPrimary,
                    modifier = Modifier.padding(end = 12.dp),
                )
            },
            onValueChange = {
                onEvent(NewProductEvent.BarcodeChanged(it))
            }
        )

        VerticalSpacer()

        Divider(modifier = Modifier.fillMaxWidth())

        VerticalSpacer()

        Text(
            text = stringResource(Res.string.new_product_nutrition_values),
            style = FitnessAppTheme.typography.labelMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )

        VerticalSpacer(size = 12.dp)

        NutritionValuesControls(
            selectedNutritionValuesChoice = state.selectedNutritionValuesChoice,
            onChoiceClicked = { choice ->
                onEvent(NewProductEvent.NutritionValuesChoiceChanged(choice))
            }
        )

        VerticalSpacer(size = 12.dp)

        TextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = state.calories,
            label = stringResource(Res.string.calories),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            onValueChange = {
                onEvent(NewProductEvent.CaloriesChanged(it))
            }
        )

        VerticalSpacer(size = 12.dp)

        TextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = state.carbohydrates,
            label = stringResource(Res.string.carbs),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            onValueChange = {
                onEvent(NewProductEvent.CarbohydratesChanged(it))
            }
        )

        VerticalSpacer(size = 12.dp)

        TextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = state.protein,
            label = stringResource(Res.string.protein),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            onValueChange = {
                onEvent(NewProductEvent.ProteinChanged(it))
            }
        )

        VerticalSpacer(size = 12.dp)

        TextField(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = state.fat,
            label = stringResource(Res.string.fat),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            onValueChange = {
                onEvent(NewProductEvent.FatChanged(it))
            }
        )
    }
}

@Composable
private fun NutritionValuesControls(
    selectedNutritionValuesChoice: NutritionValuesChoice,
    onChoiceClicked: (NutritionValuesChoice) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 16.dp)
            .background(
                color = FitnessAppTheme.colors.backgroundTertiary,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(4.dp)
    ) {
        NutritionValuesChoice.entries.forEach { choice ->
            val isChoiceActive = choice == selectedNutritionValuesChoice

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onChoiceClicked(choice) },
                    )
                    .shadow(
                        elevation = if (isChoiceActive) 8.dp else 0.dp,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .background(
                        color = if (isChoiceActive) {
                            FitnessAppTheme.colors.background
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 6.dp, horizontal = 8.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(
                        resource = when (choice) {
                            NutritionValuesChoice.IN_100_GRAMS -> Res.string.new_product_in_100_grams
                            NutritionValuesChoice.IN_CONTAINER -> Res.string.new_product_in_container
                            NutritionValuesChoice.IN_AVERAGE -> Res.string.new_product_in_average
                        }
                    ),
                    style = FitnessAppTheme.typography.labelMedium,
                    color = if (isChoiceActive) {
                        FitnessAppTheme.colors.contentPrimary
                    } else {
                        FitnessAppTheme.colors.contentSecondary
                    }
                )
            }
        }
    }
}