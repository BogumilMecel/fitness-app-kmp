package domain.model

import com.gmail.bogumilmecel2.introduction.composeResources.Res
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_none
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_training_average
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_training_high
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_training_low
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_training_very_high
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TrainingFrequency : Tile {
    @SerialName("none")
    NONE,

    @SerialName("low")
    LOW,

    @SerialName("average")
    AVERAGE,

    @SerialName("high")
    HIGH,

    @SerialName("very_high")
    VERY_HIGH;

    override fun getDisplayValue() = when (this) {
        NONE -> Res.string.introduction_none
        LOW -> Res.string.introduction_training_low
        AVERAGE -> Res.string.introduction_training_average
        HIGH -> Res.string.introduction_training_high
        VERY_HIGH -> Res.string.introduction_training_very_high
    }
} 