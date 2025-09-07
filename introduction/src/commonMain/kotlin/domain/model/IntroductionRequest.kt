package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntroductionRequest(
    @SerialName("gender")
    val gender: Gender,

    @SerialName("weight")
    val weight: Double,

    @SerialName("age")
    val age: Int,

    @SerialName("height")
    val height: Int,

    @SerialName("activity_level")
    val activityLevel: ActivityLevel,

    @SerialName("training_frequency")
    val trainingFrequency: TrainingFrequency,

    @SerialName("type_of_work")
    val typeOfWork: TypeOfWork,

    @SerialName("desired_weight")
    val desiredWeight: DesiredWeight
)