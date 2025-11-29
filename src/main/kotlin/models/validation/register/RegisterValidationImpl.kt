package models.validation.register

import models.dto.register.RegisterRequest
import models.tables.Users
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class RegisterValidationImpl : RegisterValidation {
    override fun hasUser(userDto: RegisterRequest): Boolean {
        val checkHaveUser: Boolean = transaction {
            Users.select(Users.username, Users.email)
                .where {
                    (Users.username eq userDto.username) and (Users.email eq userDto.email)
                }.any()
        }

        return checkHaveUser
    }
}