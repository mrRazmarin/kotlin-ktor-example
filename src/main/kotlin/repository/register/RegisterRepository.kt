package repository.register

import models.dto.register.RegisterRequest

interface RegisterRepository {
    fun create(user: RegisterRequest): Long
}