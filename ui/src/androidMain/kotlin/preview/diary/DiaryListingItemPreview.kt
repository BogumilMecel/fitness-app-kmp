package preview.diary

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.diary.DiaryListingItem
import theme.FitnessAppTheme

@Composable
@Preview(showBackground = true)
private fun DiaryListingItemPreviewLight() {
    DiaryListingItemPreview(darkTheme = false)
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun DiaryListingItemPreviewDark() {
    DiaryListingItemPreview(darkTheme = true)
}

@Composable
private fun DiaryListingItemPreview(darkTheme: Boolean) {
    FitnessAppTheme(darkTheme = darkTheme) {
        DiaryListingItem(
            modifier = Modifier.padding(vertical = 8.dp),
            productName = "Blueberries",
            servingSize = "1 serving (70g)",
            calories = 40,
            carbohydrates = 17.3,
            protein = 4.8,
            fat = 0.7,
            showCompact = false,
            onClick = {}
        )
    }
}