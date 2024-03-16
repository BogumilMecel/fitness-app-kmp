package theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class FitnessAppColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val calories: Color,
    val carbohydrates: Color,
    val protein: Color,
    val fat: Color,
    val background: Color,
    val surface: Color,
    val contentPrimary: Color,
    val contentSecondary: Color,
    val contentTertiary: Color,
    val onBackground: Color,
    val error: Color,
    val backgroundError: Color,
    val border: Color
)

fun lightFitnessAppColorSchema() = FitnessAppColorScheme(
    background = backgroundLight,
    surface = surfaceLight,
    error = errorLight,
    primary = primaryLight,
    onPrimary = contentPrimaryDark,
    calories = caloriesLight,
    carbohydrates = carbohydratesLight,
    protein = proteinLight,
    fat = fatLight,
    contentPrimary = contentPrimaryLight,
    contentSecondary = contentSecondaryLight,
    contentTertiary = contentTertiary,
    onBackground = contentPrimaryLight,
    backgroundError = backgroundErrorLight,
    border = borderLight
)

fun darkFitnessAppColorSchema() = FitnessAppColorScheme(
    background = backgroundDark,
    surface = surfaceDark,
    primary = primaryDark,
    onPrimary = contentPrimaryDark,
    calories = caloriesDark,
    carbohydrates = carbohydratesDark,
    protein = proteinDark,
    fat = fatDark,
    error = errorDark,
    contentPrimary = contentPrimaryDark,
    contentSecondary = contentSecondaryDark,
    contentTertiary = contentTertiary,
    onBackground = contentPrimaryDark,
    backgroundError = backgroundErrorDark,
    border = borderDark
)

private val backgroundErrorLight = Color(0xFFDE003B)
private val backgroundErrorDark = Color(0xFFF56F83)

private val contentPrimaryDark = Color.White
private val contentPrimaryLight = Color.Black

private val contentSecondaryDark = Color(0xFFbfbfbf)
private val contentSecondaryLight = Color(0xFF737373)

private val contentTertiary = Color(0xFFC0999999)

private val backgroundLight = Color(0xffffffff)
private val backgroundDark = Color(0xFF121212)

private val surfaceLight = Color(0xfffffbff)
private val surfaceDark = Color(0xff201a1a)

private val fatDark = Color(0xfff0bd28)
private val fatLight = Color(0xfff4cf65)

private val caloriesDark = Color(0xfffdbda1)
private val caloriesLight = Color(0xfff9a27b)

private val carbohydratesDark = Color(0xff54e1b6)
private val carbohydratesLight = Color(0xff11d79b)

private val proteinDark = Color(0xffaeb4fd)
private val proteinLight = Color(0xff8f98fd)

private val primaryLight = Color(0xffdb4c3e)
private val primaryDark = Color(0xffdd4c49)

private val errorLight = Color(0xffba1a1a)
private val errorDark = Color(0xffffb4ab)

private val borderLight = Color(0xFFE6E6E6)
private val borderDark = Color(0xFF363636)