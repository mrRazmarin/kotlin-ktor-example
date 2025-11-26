package models.validation

abstract class AbstractValidator {

    fun isNotEmpty(value: String?): Boolean = value != null && value.isNotBlank()

    protected fun isEmail(value: String): Boolean = value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex())

    protected fun checkPresenceCapitalLetter(value: String): Boolean = value.any { it.isLetter() && it.isUpperCase() }

    protected fun hasMaxLength(value: String, maxLength: Int): Boolean = value.count() <= maxLength

    protected fun hasMinLength(value: String, minLength: Int): Boolean = value.count() >= minLength

    protected fun isNumeric(value: Any): Boolean = value is Int || value is Long

}