package models.dto.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val message: String
)
