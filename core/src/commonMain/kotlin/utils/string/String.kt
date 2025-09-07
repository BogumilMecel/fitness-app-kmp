package utils.string

fun String.isLengthInRange(maximum: Int, minimum: Int = 1): Boolean = length in minimum..maximum

fun formatTwoValues(
    value1: String,
    value2: String,
    format: FormatTwoValues
) = "$value1${format.value}$value2"

enum class FormatTwoValues(val value: String) {
    SEMICOLON(":");
}

val String.Companion.EMPTY
    get() = ""

val String.Companion.SPACE
    get() = " "

val String.Companion.SEMICOLON
    get() = ":"

val String.Companion.PERCENTAGE
    get() = "%"

fun String.normalizeDecimalSeparator(): String {
    return replace(",", ".")
}

fun String.toValidInt(): Int? = trim().toIntOrNull()

fun String.toValidDouble(): Double? = trim().normalizeDecimalSeparator().toDoubleOrNull()