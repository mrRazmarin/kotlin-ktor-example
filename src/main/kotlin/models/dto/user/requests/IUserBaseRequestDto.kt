package models.dto.user.requests

import kotlinx.serialization.Serializable

@Serializable
interface IUserBaseRequestDto {
    val username: String
    val email: String
}