package models.validation.register

import models.dto.register.RegisterRequest
import models.tables.Users
import models.validation.AbstractValidator
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class RegisterValidationImpl : AbstractValidator<RegisterRequest>(), RegisterValidation {
    override fun hasUser(userDto: RegisterRequest): Boolean {
        val checkHaveUser: Boolean = transaction {
            Users.select(Users.username, Users.email)
                .where {
                    (Users.username eq userDto.username) and (Users.email eq userDto.email)
                }.any()
        }

        return checkHaveUser
    }

    override fun validateAll(dto: RegisterRequest): List<String> {
        val errors: MutableList<String> = mutableListOf()
        if (isBlank(dto.username)) errors.add("Field 'username' don't must be null")
        if (isBlank(dto.email)) errors.add("Field 'email' don't must be null")
        if (isBlank(dto.password)) errors.add("Field 'password' don't must be null")
        if (isBlank(dto.retryPassword)) errors.add("Field 'retryPassword' don't must be null")

        return errors
    }
}