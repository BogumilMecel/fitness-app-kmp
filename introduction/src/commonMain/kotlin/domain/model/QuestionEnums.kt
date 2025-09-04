package domain.model

import com.gmail.bogumilmecel2.introduction.composeResources.Res
import com.gmail.bogumilmecel2.introduction.composeResources.*

enum class QuestionName {
    GENDER, AGE, HEIGHT, CURRENT_WEIGHT, TYPE_OF_WORK, TRAINING_FREQUENCY, ACTIVITY_LEVEL, DESIRED_WEIGHT;

    fun getQuestionTitle() = when (this) {
        GENDER -> Res.string.what_is_your_gender
        AGE -> Res.string.what_is_your_age
        HEIGHT -> Res.string.what_is_your_height
        CURRENT_WEIGHT -> Res.string.what_is_your_current_weight
        TYPE_OF_WORK -> Res.string.what_type_of_work_do_you_have
        TRAINING_FREQUENCY -> Res.string.how_often_do_you_train_in_a_week
        ACTIVITY_LEVEL -> Res.string.how_would_you_describe_your_activity_level_during_a_day
        DESIRED_WEIGHT -> Res.string.what_is_your_desired_weight
    }

    fun getQuestionType() = when (this) {
        GENDER, TYPE_OF_WORK, TRAINING_FREQUENCY, ACTIVITY_LEVEL, DESIRED_WEIGHT -> QuestionType.TILE
        AGE, HEIGHT, CURRENT_WEIGHT -> QuestionType.INPUT
    }

    fun getQuestionUnit() = when(this) {
        HEIGHT -> Res.string.cm
        CURRENT_WEIGHT -> Res.string.kg
        else -> null
    }

    fun getQuestionTiles(): List<Tile> = when (this) {
        GENDER -> Gender.entries
        TYPE_OF_WORK -> TypeOfWork.entries
        ACTIVITY_LEVEL -> ActivityLevel.entries
        TRAINING_FREQUENCY -> TrainingFrequency.entries
        DESIRED_WEIGHT -> DesiredWeight.entries
        else -> emptyList()
    }
}

enum class QuestionType {
    TILE, INPUT
} 