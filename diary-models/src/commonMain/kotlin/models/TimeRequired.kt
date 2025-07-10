package models

import kotlinx.serialization.Serializable

@Serializable
enum class TimeRequired {
    LOW,
    AVERAGE,
    HIGH,
    MORE;
} 