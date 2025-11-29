package models.dto.user.responses

data class UserResponseDto(
    val id: Long,
    override val username: String,
    val password: String
) : IUserBaseResponseDto

data class UserResponseForAdminDto(
    val id: Long,
    override val username: String
) : IUserBaseResponseDto