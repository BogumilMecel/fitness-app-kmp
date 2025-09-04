package domain.model

import com.gmail.bogumilmecel2.introduction.composeResources.Res
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_gain
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_keep
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_loose
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DesiredWeight : Tile {
    @SerialName("loose")
    LOOSE,

    @SerialName("keep")
    KEEP,

    @SerialName("gain")
    GAIN;

    override fun getDisplayValue() = when(this) {
        LOOSE -> Res.string.introduction_loose
        GAIN -> Res.string.introduction_gain
        KEEP -> Res.string.introduction_keep
    }
} 