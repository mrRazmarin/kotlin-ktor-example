package models.dto.user.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val id: Long,
    override val username: String,
    override val email: String,
    val password: String
) : IUserRequestDto