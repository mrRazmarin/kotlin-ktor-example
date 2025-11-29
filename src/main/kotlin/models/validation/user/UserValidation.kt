package models.validation.user

import models.dto.user.requests.IUserBaseRequestDto
import models.validation.AbstractValidator

class UserValidation : AbstractValidator() {

    private val MAX_LENGTH: Int = 25
    private val MIN_LENGTH: Int = 3

    fun hasMaxLength(dto: IUserBaseRequestDto): String =
        if (!hasMaxLength(dto.username, MAX_LENGTH))
            "Username must be less than $MAX_LENGTH characters long"
        else ""

    fun hasMinLength(dto: IUserBaseRequestDto): String =
        if (!hasMinLength(dto.username, MIN_LENGTH))
            "Username must be more than $MIN_LENGTH characters long"
        else ""

    // Общий метод: собираем все ошибки в список
    fun validateAll(dto: IUserBaseRequestDto): List<String> =
        listOf(
            hasMaxLength(dto),
            hasMinLength(dto)
        ).filter { it.isNotBlank() }
}