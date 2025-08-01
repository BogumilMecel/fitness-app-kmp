package presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.product_weight
import components.Button
import components.MediumButtonTextContent
import components.TextField
import components.SheetTopBarWithEndButton
import org.jetbrains.compose.resources.stringResource
import theme.FitnessAppTheme
import utils.string.SPACE

@Composable
fun ProductScreen(
    state: ProductState,
    onEvent: (ProductEvent) -> Unit,
) {
    Column {
        SheetTopBarWithEndButton(
            title = state.productName,
            endButtonTextColor = FitnessAppTheme.colors.primary,
            endButtonText = "SAVE",
            onEndButtonClicked = {
                onEvent(ProductEvent.OnSaveClicked)
            },
            onBackPressed = {
                onEvent(ProductEvent.OnBackPressed)
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                text = state.weight,
                label = stringResource(Res.string.product_weight) + String.SPACE +  "(${state.productMeasurementUnit.longDisplayName})",
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ),
                onValueChange = {
                    onEvent(ProductEvent.WeightChanged(it))
                }
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = listOf(50, 100, 150, 200)) { weight ->
                Button(
                    backgroundColor = FitnessAppTheme.colors.primary,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onEvent(ProductEvent.OnQuickWeightButtonClicked(weight))
                    },
                    content = {
                        MediumButtonTextContent(text = "$weight ${state.productMeasurementUnit.longDisplayName}")
                    }
                )
            }
        }

//        Button(
//            backgroundColor = FitnessAppTheme.colors.primary,
//            onClick = {
//                onEvent(ProductEvent.OnSaveClicked)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            LargeButtonTextContent(
//                text = stringResource(Res.string.product_save_to)
//                        + String.DOT_WITH_SPACES
//                        + state.mealName.displayName
//                        + String.DOT_WITH_SPACES
//                        + state.date.getDisplayValue()
//            )
//        }
    }
}