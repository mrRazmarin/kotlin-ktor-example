package models.dto.user.requests

import kotlinx.serialization.Serializable

// DTO для создания пользователя
@Serializable
data class UserCreateDto(
    override val username: String,
    override val email: String,
    val password: String
) : IUserBaseRequestDto
