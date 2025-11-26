package repository

import models.dto.user.UserCreateDto
import models.dto.user.UserResponseDto
import models.tables.Users
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

// Реализация методов из интерфейса
class UserRepositoryImpl : UserRepository {
    override fun getAll(): List<UserResponseDto> = transaction {
        Users.selectAll().map {
            UserResponseDto(it[Users.id], it[Users.username], it[Users.email])
        }
    }

    override fun add(user: UserCreateDto) {
        transaction {
            Users.insert {
                it[username] = user.username    // Явно указываем, что кладём в поле username значение из dto
                it[email] = user.email          // Аналогично для поля email
            }
        }
    }

    override fun change(id: Long, update: UserCreateDto): Boolean = transaction {
        Users.update({
            Users.id eq id
        }) {
            it[username] = update.username
            it[email] = update.email
        } > 0 // Проверяем, сколько записей было изменено. Если больше нуля, то true, если меньше, то false
    }

    override fun delete(id: Long): Boolean = transaction {
        Users.deleteWhere {
            Users.id eq id
        } > 0 // Проверяем, сколько записей было удалено. Если больше нуля, то true, если меньше, то false
    }
}

