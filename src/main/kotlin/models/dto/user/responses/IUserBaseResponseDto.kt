package models.dto.user.responses

import kotlinx.serialization.Serializable

@Serializable
interface IUserBaseResponseDto {
    val username: String
}