package domain.model

import com.gmail.bogumilmecel2.introduction.composeResources.Res
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_activity_high
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_activity_low
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_activity_moderate
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_activity_very_high
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ActivityLevel : Tile {
    @SerialName("low")
    LOW,

    @SerialName("moderate")
    MODERATE,

    @SerialName("high")
    HIGH,

    @SerialName("very_high")
    VERY_HIGH;

    override fun getDisplayValue() = when (this) {
        LOW -> Res.string.introduction_activity_low
        MODERATE -> Res.string.introduction_activity_moderate
        HIGH -> Res.string.introduction_activity_high
        VERY_HIGH -> Res.string.introduction_activity_very_high
    }
} 