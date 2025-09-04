package presentation

import domain.model.ActivityLevel
import domain.model.DesiredWeight
import domain.model.Gender
import domain.model.QuestionName
import domain.model.Tile
import domain.model.TrainingFrequency
import domain.model.TypeOfWork

data class IntroductionState(
    val selectedGender: Gender = Gender.MALE,
    val age: String = "18",
    val height: String = "175",
    val currentWeight: String = "70",
    val typeOfWork: TypeOfWork = TypeOfWork.SEDENTARY,
    val activityLevel: ActivityLevel = ActivityLevel.LOW,
    val trainingFrequency: TrainingFrequency = TrainingFrequency.NONE,
    val desiredWeight: DesiredWeight = DesiredWeight.LOOSE,
) {
    fun getStringAnswer(questionName: QuestionName): String? = when(questionName) {
        QuestionName.AGE -> this.age
        QuestionName.CURRENT_WEIGHT -> this.currentWeight
        QuestionName.HEIGHT -> this.height
        else -> null
    }
    fun getTileAnswer(questionName: QuestionName): Tile? = when (questionName) {
        QuestionName.GENDER -> this.selectedGender
        QuestionName.TYPE_OF_WORK -> this.typeOfWork
        QuestionName.TRAINING_FREQUENCY -> this.trainingFrequency
        QuestionName.ACTIVITY_LEVEL -> this.activityLevel
        QuestionName.DESIRED_WEIGHT -> this.desiredWeight
        else -> null
    }
}