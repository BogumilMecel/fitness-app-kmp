package preview.diary

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.diary.DiaryNutritionItem
import theme.FitnessAppTheme

@Composable
@Preview(showBackground = true)
private fun DiaryNutritionItemPreviewLight() {
    DiaryNutritionItemPreview(darkTheme = false)
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun DiaryNutritionItemPreviewDark() {
    DiaryNutritionItemPreview(darkTheme = true)
}

@Composable
private fun DiaryNutritionItemPreview(darkTheme: Boolean) {
    FitnessAppTheme(darkTheme = darkTheme) {
        DiaryNutritionItem(
            modifier = Modifier.padding(8.dp),
            name = "Protein",
            value = 15.2,
            color = FitnessAppTheme.colors.protein,
        )
    }
}