package models

// DTO для представления пользователя
data class User(
    val id: Long,
    val username: String,
    val email: String
)