package models.tables

import org.jetbrains.exposed.sql.Table

// Таблица пользователей (описание структуры для Exposed)
object Users : Table() {
    val id = long("id").autoIncrement()
    val username = varchar("username", 50)
    val email = varchar("email", 100)
    val password = varchar("password", 50)

    override val primaryKey = PrimaryKey(id)
}