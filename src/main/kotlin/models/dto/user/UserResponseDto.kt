package models.dto.user

data class UserResponseDto(
    val id: Long,
    override val username: String,
    override val email: String
) : IUserBaseDto