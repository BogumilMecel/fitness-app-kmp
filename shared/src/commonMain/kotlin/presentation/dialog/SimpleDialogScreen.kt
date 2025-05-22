package presentation.dialog

//import androidx.compose.foundation.layout.Column
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.unit.dp
//import cafe.adriel.voyager.core.screen.Screen
//import components.FitnessAppButton
//import components.FitnessAppButtonStyle
//import components.VerticalSpacer
//import presentation.ModelLayout
//import theme.FitnessAppTheme

//class SimpleDialogScreen(
//    private val title: String,
//    private val description: String,
//    private val firstButtonText: String,
//    private val secondButtonText: String,
//) : Screen {
//
//    @Composable
//    override fun Content() {
//        ModelLayout<SimpleDialogScreenModel> {
//            Column {
//                Text(
//                    text = title,
//                    style = FitnessAppTheme.typography.headlineLarge,
//                    color = FitnessAppTheme.colors.contentPrimary,
//                )
//
//                VerticalSpacer(size = 16.dp)
//
//                Text(
//                    text = description,
//                    style = FitnessAppTheme.typography.bodyMedium,
//                    color = FitnessAppTheme.colors.contentPrimary,
//                )
//
//                VerticalSpacer(size = 24.dp)
//
//                FitnessAppButton(
//                    style = FitnessAppButtonStyle.Primary,
//                    text = firstButtonText,
//                    onClick = ::onFirstButtonClicked,
//                )
//
//                VerticalSpacer(size = 8.dp)
//
//                FitnessAppButton(
//                    style = FitnessAppButtonStyle.Secondary,
//                    text = secondButtonText,
//                    onClick = ::onSecondButtonClicked
//                )
//
//                VerticalSpacer(size = 12.dp)
//            }
//        }
//    }
//}