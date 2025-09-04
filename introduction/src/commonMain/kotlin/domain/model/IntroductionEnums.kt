package domain.model

import com.gmail.bogumilmecel2.introduction.composeResources.Res
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_activity_high
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_activity_low
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_activity_moderate
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_activity_very_high
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_female
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_gain
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_keep
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_loose
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_male
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_mixed
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_none
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_physical
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_sedentary
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_training_average
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_training_high
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_training_low
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_training_very_high
import org.jetbrains.compose.resources.StringResource

enum class Gender : Tile {
    MALE, FEMALE;

    override fun getDisplayValue() = when (this) {
        MALE -> Res.string.introduction_male
        FEMALE -> Res.string.introduction_female
    }
}

enum class TypeOfWork : Tile {
    SEDENTARY, MIXED, PHYSICAL;

    override fun getDisplayValue() = when (this) {
        SEDENTARY -> Res.string.introduction_sedentary
        MIXED -> Res.string.introduction_mixed
        PHYSICAL -> Res.string.introduction_physical
    }
}

enum class TrainingFrequency : Tile {
    NONE, LOW, AVERAGE, HIGH, VERY_HIGH;

    override fun getDisplayValue() = when (this) {
        NONE -> Res.string.introduction_none
        LOW -> Res.string.introduction_training_low
        AVERAGE -> Res.string.introduction_training_average
        HIGH -> Res.string.introduction_training_high
        VERY_HIGH -> Res.string.introduction_training_very_high
    }
}

enum class ActivityLevel : Tile {
    LOW, MODERATE, HIGH, VERY_HIGH;

    override fun getDisplayValue() = when (this) {
        LOW -> Res.string.introduction_activity_low
        MODERATE -> Res.string.introduction_activity_moderate
        HIGH -> Res.string.introduction_activity_high
        VERY_HIGH -> Res.string.introduction_activity_very_high
    }
}

enum class DesiredWeight : Tile {
    LOOSE, KEEP, GAIN;

    override fun getDisplayValue() = when(this) {
        LOOSE -> Res.string.introduction_loose
        GAIN -> Res.string.introduction_gain
        KEEP -> Res.string.introduction_keep
    }
}



interface Tile {
    fun getDisplayValue(): StringResource
}