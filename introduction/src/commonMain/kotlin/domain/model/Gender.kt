package domain.model

import com.gmail.bogumilmecel2.introduction.composeResources.Res
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_female
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_male
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Gender : Tile {
    @SerialName("male")
    MALE,

    @SerialName("female")
    FEMALE;

    override fun getDisplayValue() = when (this) {
        MALE -> Res.string.introduction_male
        FEMALE -> Res.string.introduction_female
    }
} 