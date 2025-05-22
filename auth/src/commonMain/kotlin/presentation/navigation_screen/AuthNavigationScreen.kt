package presentation.navigation_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.email
import com.gmail.bogumilmecel2.ui.composeResources.google
import com.gmail.bogumilmecel2.ui.composeResources.or_
import com.gmail.bogumilmecel2.ui.composeResources.sign_up_with_email
import com.gmail.bogumilmecel2.ui.composeResources.sing_in_with
import com.gmail.bogumilmecel2.ui.composeResources.welcome_to
import components.Divider
import components.FitnessAppButton
import components.FitnessAppButtonStyle
import components.FitnessAppTopBar
import components.HorizontalSpacer
import dev.sergiobelda.compose.vectorize.images.Images
import dev.sergiobelda.compose.vectorize.images.icons.GoogleLogo
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.FitnessAppTheme

@Composable
fun AuthNavigationScreen(viewModel: AuthNavigationModel = koinViewModel()) = with(viewModel) {
    FitnessAppTopBar(title = stringResource(Res.string.welcome_to))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(Res.string.sing_in_with),
            color = FitnessAppTheme.colors.contentPrimary,
            style = FitnessAppTheme.typography.labelLarge
        )

        HorizontalSpacer()

        FitnessAppButton(
            text = stringResource(Res.string.email),
            style = FitnessAppButtonStyle.Content,
            startIcon = Icons.Default.Email,
            onClick = ::onSignInWithEmailClicked
        )

        HorizontalSpacer()

        FitnessAppButton(
            text = stringResource(Res.string.google),
            style = FitnessAppButtonStyle.Content,
            startIcon = Images.Icons.GoogleLogo,
            iconColor = Color.Unspecified,
            onClick = ::onSignInWithGoogleClicked
        )

        Divider(
            modifier = Modifier.padding(vertical = 16.dp),
            text = stringResource(Res.string.or_)
        )

        FitnessAppButton(
            text = stringResource(Res.string.sign_up_with_email),
            style = FitnessAppButtonStyle.Content,
            startIcon = Icons.Default.Person,
            onClick = ::onSignUpWithEmailClicked
        )
    }
}