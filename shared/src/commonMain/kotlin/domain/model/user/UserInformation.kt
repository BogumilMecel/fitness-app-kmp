package domain.model.user

import domain.model.ActivityLevel
import domain.model.DesiredWeight
import domain.model.Gender
import domain.model.TrainingFrequency
import domain.model.TypeOfWork
import kotlinx.serialization.Serializable

@Serializable
data class UserInformation (
    val activityLevel: ActivityLevel = ActivityLevel.LOW,
    val typeOfWork: TypeOfWork = TypeOfWork.SEDENTARY,
    val trainingFrequency: TrainingFrequency = TrainingFrequency.LOW,
    val gender: Gender = Gender.MALE,
    val height: Int = 0,
    val currentWeight: Double = 0.0,
    val desiredWeight: DesiredWeight = DesiredWeight.KEEP,
    val age: Int = 0
)