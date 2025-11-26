package db

import config.AppConfig
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    fun init(config: AppConfig) {
        Database.connect(
            url = config.jdbcUrl,
            driver = "org.postgresql.Driver",
            user = config.dbUser,
            password = config.dbPassword
        )
    }
}