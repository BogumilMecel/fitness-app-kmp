package theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.gmail.bogumilmecel2.ui.SharedRes
import dev.icerock.moko.resources.compose.asFont

@Immutable
data class FitnessAppTypography(
    val headlineLarge: TextStyle = headline(fontSize = 24.sp, lineHeight = 32.sp),
    val headlineMedium: TextStyle = headline(fontSize = 20.sp, lineHeight = 28.sp),
    val headlineSmall: TextStyle = headline(fontSize = 18.sp, lineHeight = 24.sp),

    val titleLarge: TextStyle = title(fontSize = 24.sp, lineHeight = 32.sp),
    val titleMedium: TextStyle = title(fontSize = 20.sp, lineHeight = 28.sp),
    val titleSmall: TextStyle = title(fontSize = 18.sp, lineHeight = 24.sp),

    val bodyLarge: TextStyle = body(fontSize = 16.sp, lineHeight = 24.sp),
    val bodyMedium: TextStyle = body(fontSize = 14.sp, lineHeight = 20.sp),
    val bodySmall: TextStyle = body(fontSize = 12.sp, lineHeight = 16.sp),

    val labelLarge: TextStyle = label(fontSize = 16.sp, lineHeight = 24.sp),
    val labelMedium: TextStyle = label(fontSize = 14.sp, lineHeight = 20.sp),
    val labelSmall: TextStyle = label(fontSize = 12.sp, lineHeight = 16.sp),
)

@Composable
fun FitnessAppTypography.applyFontFamily(fontFamily: FontFamily = roboto) = copy(
    headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
    headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
    headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
    titleLarge = titleLarge.copy(fontFamily = fontFamily),
    titleMedium = titleMedium.copy(fontFamily = fontFamily),
    titleSmall = titleSmall.copy(fontFamily = fontFamily),
    bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
    bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
    bodySmall = bodySmall.copy(fontFamily = fontFamily),
    labelLarge = labelLarge.copy(fontFamily = fontFamily),
    labelMedium = labelMedium.copy(fontFamily = fontFamily),
    labelSmall = labelSmall.copy(fontFamily = fontFamily)
)

private fun headline(fontSize: TextUnit, lineHeight: TextUnit) =
    textStyle(fontSize = fontSize, fontWeight = FontWeight.Bold, lineHeight = lineHeight)

private fun title(fontSize: TextUnit, lineHeight: TextUnit) =
    textStyle(fontSize = fontSize, fontWeight = FontWeight.Normal, lineHeight = lineHeight)

private fun label(fontSize: TextUnit, lineHeight: TextUnit) =
    textStyle(fontSize = fontSize, fontWeight = FontWeight.SemiBold, lineHeight = lineHeight)

private fun body(fontSize: TextUnit, lineHeight: TextUnit) =
    textStyle(fontSize = fontSize, fontWeight = FontWeight.Normal, lineHeight = lineHeight)

private fun textStyle(fontSize: TextUnit, fontWeight: FontWeight, lineHeight: TextUnit) =
    TextStyle(
        fontSize = fontSize,
        fontWeight = fontWeight,
        lineHeight = lineHeight,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    )

private val roboto
    @Composable get() = FontFamily(
        SharedRes.fonts.roboto.regular.asFont(weight = FontWeight.Normal)!!,
        SharedRes.fonts.roboto.medium.asFont(weight = FontWeight.Medium)!!,
        SharedRes.fonts.roboto.bold.asFont(weight = FontWeight.Bold)!!,
    )