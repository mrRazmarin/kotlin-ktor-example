package routes.auth

import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.post
import io.ktor.server.routing.*
import jwt.JwtConfig
import models.LoginRequest
import org.koin.ktor.ext.get

fun Application.authRoutes() {
    val jwtConfig = get<JwtConfig>()

    routing {
        post("/login") {
            val login = call.receive<LoginRequest>()

            // Обычно проверяется пароль и наличие такого пользователя
            val token = jwtConfig.generateToken(login.username)
            call.respond(mapOf("token" to token))
        }

        authenticate("auth-jwt") {
            get("/protectedCheck") {
                call.respondText("You are authenticated!")
            }
        }
    }
}