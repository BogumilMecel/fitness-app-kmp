package components

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.gmail.bogumilmecel2.ui.SharedRes
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import sharedComponents.VectorIcon
import theme.FitnessAppTheme

@Composable
fun FitnessAppIcon(
    modifier: Modifier = Modifier,
    icon: Icon,
    tint: Color = FitnessAppTheme.colors.contentPrimary
) {
    when (icon) {
        is IconPainter -> {
            FitnessAppPainterIcon(
                painter = icon,
                tint = tint,
                modifier = modifier
            )
        }

        is IconVector -> {
            FitnessAppVectorIcon(
                vector = icon,
                tint = tint,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun FitnessAppVectorIcon(
    modifier: Modifier = Modifier,
    vector: IconVector,
    tint: Color
) = with(vector) {
    Icon(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = stringResource(resource = vector.contentDescriptionResource),
        tint = tint
    )
}

@Composable
private fun FitnessAppPainterIcon(
    modifier: Modifier = Modifier,
    painter: IconPainter,
    tint: Color = FitnessAppTheme.colors.contentPrimary
) = with(painter) {
    VectorIcon(
        imageResource = imageResource,
        contentDescription = stringResource(painter.contentDescriptionResource),
    )
}

interface Icon {
    val contentDescriptionResource: StringResource
}

sealed class IconPainter(
    val imageResource: ImageResource,
    override val contentDescriptionResource: StringResource
) : Icon {
    data object Google : IconPainter(
        imageResource = SharedRes.images.google_logo,
        contentDescriptionResource = SharedRes.strings.account
    )
}

sealed class IconVector(
    val imageVector: ImageVector,
    override val contentDescriptionResource: StringResource
) : Icon {
    data object Heart : IconVector(
        imageVector = Icons.Default.FavoriteBorder,
        contentDescriptionResource = SharedRes.strings.favorite
    )

    data object HeartFilled : IconVector(
        imageVector = Icons.Filled.Favorite,
        contentDescriptionResource = SharedRes.strings.favorite_filled
    )

    data object Back : IconVector(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescriptionResource = SharedRes.strings.back
    )

    data object Search : IconVector(
        imageVector = Icons.Default.Search,
        contentDescriptionResource = SharedRes.strings.search
    )

    data object Cancel : IconVector(
        imageVector = Icons.Default.Search,
        contentDescriptionResource = SharedRes.strings.cancel
    )

    data object Add : IconVector(
        imageVector = Icons.Default.Add,
        contentDescriptionResource = SharedRes.strings.add
    )

    data object Logout : IconVector(
        imageVector = Icons.AutoMirrored.Filled.Logout,
        contentDescriptionResource = SharedRes.strings.log_out
    )

    data object Save : IconVector(
        imageVector = Icons.Default.Save,
        contentDescriptionResource = SharedRes.strings.save
    )

    data object Edit : IconVector(
        imageVector = Icons.Default.Edit,
        contentDescriptionResource = SharedRes.strings.edit
    )

    data object Email : IconVector(
        imageVector = Icons.Default.Email,
        contentDescriptionResource = SharedRes.strings.email
    )

    data object Password : IconVector(
        imageVector = Icons.Default.Password,
        contentDescriptionResource = SharedRes.strings.password
    )

    data object Login : IconVector(
        imageVector = Icons.AutoMirrored.Filled.Login,
        contentDescriptionResource = SharedRes.strings.login
    )

    data object Register : IconVector(
        imageVector = Icons.Default.PersonAdd,
        contentDescriptionResource = SharedRes.strings.register
    )

    data object Account : IconVector(
        imageVector = Icons.Default.AccountCircle,
        contentDescriptionResource = SharedRes.strings.account
    )

    data object Info : IconVector(
        imageVector = Icons.Default.Info,
        contentDescriptionResource = SharedRes.strings.info
    )

    data object ArrowDown : IconVector(
        imageVector = Icons.Default.KeyboardArrowDown,
        contentDescriptionResource = SharedRes.strings.arrow_down
    )

    data object Copy : IconVector(
        imageVector = Icons.Default.ContentCopy,
        contentDescriptionResource = SharedRes.strings.copy
    )

    data object Warning : IconVector(
        imageVector = Icons.Default.Error,
        contentDescriptionResource = SharedRes.strings.error
    )

    data object Clear : IconVector(
        imageVector = Icons.Default.Clear,
        contentDescriptionResource = SharedRes.strings.clear
    )
}