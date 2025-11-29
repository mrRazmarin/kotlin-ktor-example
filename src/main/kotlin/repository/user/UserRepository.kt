package repository.user

import models.dto.user.requests.UserResponseDto
import models.dto.user.requests.UserCreateDto

// Интерфейс-репозиторий для CRUD операций с сущностью User
interface UserRepository {
    // Получить всех пользователей
    fun getAll(): List<UserResponseDto>

    // Затем самостоятельно реализовать другие методы CRUD...

    fun add(user: UserCreateDto)

    fun change(id: Long, update: UserCreateDto): Boolean

    fun delete(id: Long): Boolean
}