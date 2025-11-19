package preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import components.AppTopBar
import theme.FitnessAppTheme

@Composable
@Preview(showBackground = true)
private fun TopBarPreviewLight() {
    TopBarPreview(darkTheme = false)
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun TopBarPreviewDark() {
    TopBarPreview(darkTheme = true)
}

@Composable
@Preview(showBackground = true)
private fun TopBarWithoutSubtitlePreviewLight() {
    TopBarPreview(
        subTitle = null,
        darkTheme = false
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun TopBarWithoutSubtitlePreviewDark() {
    TopBarPreview(
        subTitle = null,
        darkTheme = true
    )
}

@Composable
private fun TopBarPreview(
    title: String? = "Chicken And Rice",
    subTitle: String? = "Dinner",
    withBackButton: Boolean = true,
    darkTheme: Boolean,
) {
    FitnessAppTheme(darkTheme = darkTheme) {
        AppTopBar(
            title = title,
            subTitle = subTitle,
            onBackPressed = {}.takeIf { withBackButton }
        )
    }
}