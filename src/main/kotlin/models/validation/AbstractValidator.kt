package models.validation

abstract class AbstractValidator<T> : IValidator<T> {

    override fun hasError(dto: T): Boolean = validateAll(dto).isNotEmpty()

    abstract override fun validateAll(dto: T): List<String>

    protected fun isBlank(value: String): Boolean = value.isBlank()

    protected fun checkPresenceCapitalLetter(value: String): Boolean = value.any { it.isLetter() && it.isUpperCase() }

    protected fun hasMaxLength(value: String, maxLength: Int): Boolean = value.count() <= maxLength

    protected fun hasMinLength(value: String, minLength: Int): Boolean = value.count() >= minLength

    protected fun isNumeric(value: T): Boolean = value is Int || value is Long

}