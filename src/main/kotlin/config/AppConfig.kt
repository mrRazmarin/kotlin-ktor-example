package config

import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.ApplicationEnvironment

class AppConfig(env: ApplicationEnvironment) {
    private val dotenv = dotenv()

    // Database
    val jdbcUrl: String = env.config.property("ktor.db.jdbcURL").getString() // Строка подключение
    val dbUser: String = env.config.property("ktor.db.user").getString() // Имя пользователя
    val dbPassword: String = env.config.property("ktor.db.password").getString()

    //JWT
    val jwtSecret: String = env.config.property("ktor.jwt.secret").getString()
    val jwtIssuer: String = env.config.property("ktor.jwt.issuer").getString()
    val jwtAudience: String = env.config.property("ktor.jwt.audience").getString()
    val jwtRealm: String = env.config.property("ktor.jwt.realm").getString()
}