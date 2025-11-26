package models.dto.user

// DTO для создания пользователя
data class UserCreateDto(
    override val username: String,
    override val email: String
) : IUserBaseDto
