package string

fun formatTwoValues(
    value1: String,
    value2: String,
    format: FormatTwoValues
) = "$value1${format.value}$value2"

enum class FormatTwoValues(val value: String) {
    SEMICOLON(":");
}