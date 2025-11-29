package models.dto.user.requests

import kotlinx.serialization.Serializable

@Serializable
interface IUserRequestDto {
    val username: String
    val email: String
}