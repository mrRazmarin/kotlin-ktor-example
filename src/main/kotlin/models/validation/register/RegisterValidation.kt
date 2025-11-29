package models.validation.register

import models.dto.register.RegisterRequest

interface RegisterValidation {
    // Форматная валидация
    fun validateAll(dto: RegisterRequest): List<String>
    fun hasError(dto: RegisterRequest): Boolean

    fun hasUser(userDto: RegisterRequest): Boolean
}