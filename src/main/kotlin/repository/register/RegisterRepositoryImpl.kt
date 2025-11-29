package repository.register

import models.dto.register.RegisterRequest
import models.tables.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class RegisterRepositoryImpl : RegisterRepository {
    override fun create(user: RegisterRequest) = transaction {
        Users.insert {
            it[username] = user.username
            it[email] = user.email
            it[password] = user.password
        } [Users.id]
    }
}
