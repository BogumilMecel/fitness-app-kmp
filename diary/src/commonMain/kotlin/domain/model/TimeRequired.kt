package domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class TimeRequired {
    LOW,
    AVERAGE,
    HIGH,
    MORE;
}