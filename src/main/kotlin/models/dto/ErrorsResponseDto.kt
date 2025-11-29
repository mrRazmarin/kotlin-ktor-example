package models.dto

data class ErrorsResponseDto(
    val listErrors: List<ErrorDto>
)

data class ErrorDto(
    val errorMessage: String
)
