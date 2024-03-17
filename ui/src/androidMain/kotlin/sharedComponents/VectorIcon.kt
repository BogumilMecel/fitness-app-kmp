package sharedComponents

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.gmail.bogumilmecel2.ui.SharedRes
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
actual fun VectorIcon(
    imageResource: ImageResource,
    contentDescription: String?
) {
    Icon(
        painter = painterResource(SharedRes.images.google_logo),
        contentDescription = null,
        tint = Color.Unspecified
    )
}