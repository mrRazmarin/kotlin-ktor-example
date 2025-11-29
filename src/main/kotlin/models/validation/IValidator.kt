package models.validation

interface IValidator<T> {

    fun validateAll(dto: T): List<String>

    fun hasError(dto: T): Boolean

}