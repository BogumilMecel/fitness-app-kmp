@file:Suppress("unused")

package domain.model

enum class Gender {
    MALE, FEMALE;
}

enum class TypeOfWork {
    SEDENTARY, MIXED, PHYSICAL;
}

enum class TrainingFrequency {
    NONE, LOW, AVERAGE, HIGH, VERY_HIGH;
}

enum class ActivityLevel {
    LOW, MODERATE, HIGH, VERY_HIGH;
}

enum class DesiredWeight {
    LOOSE, KEEP, GAIN;
}