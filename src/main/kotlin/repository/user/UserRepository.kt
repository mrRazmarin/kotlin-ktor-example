package repository.user

import models.dto.user.requests.UserResponseDto
import models.dto.user.requests.UserCreateDto

// Интерфейс-репозиторий для CRUD операций с сущностью User
interface UserRepository {
    fun getAll(): List<UserResponseDto>

    fun add(user: UserCreateDto)

    fun change(id: Long, update: UserCreateDto): Boolean

    fun delete(id: Long): Boolean
}