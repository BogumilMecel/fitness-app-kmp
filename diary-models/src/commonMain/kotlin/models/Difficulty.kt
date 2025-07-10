package models

import kotlinx.serialization.Serializable

@Serializable
enum class Difficulty {
    VERY_LOW,
    LOW,
    AVERAGE,
    HIGH,
    VERY_HIGH,
} 