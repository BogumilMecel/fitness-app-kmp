package domain.model

import com.gmail.bogumilmecel2.introduction.composeResources.Res
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_mixed
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_physical
import com.gmail.bogumilmecel2.introduction.composeResources.introduction_sedentary
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TypeOfWork : Tile {
    @SerialName("sedentary")
    SEDENTARY,

    @SerialName("mixed")
    MIXED,

    @SerialName("physical")
    PHYSICAL;

    override fun getDisplayValue() = when (this) {
        SEDENTARY -> Res.string.introduction_sedentary
        MIXED -> Res.string.introduction_mixed
        PHYSICAL -> Res.string.introduction_physical
    }
} 