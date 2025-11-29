package models.validation.register

import models.dto.register.RegisterRequest

interface RegisterValidation {
    fun hasUser(userDto: RegisterRequest): Boolean
}