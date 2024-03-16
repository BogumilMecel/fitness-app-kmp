package utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.FitnessAppTheme

@Composable
fun getDefaultRootModifier() = Modifier
    .fillMaxSize()
    .background(color = FitnessAppTheme.colors.background)
    .statusBarsPadding()