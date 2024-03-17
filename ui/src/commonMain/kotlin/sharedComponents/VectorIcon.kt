package sharedComponents

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.ImageResource

@Composable
expect fun VectorIcon(
    imageResource: ImageResource,
    contentDescription: String?
)