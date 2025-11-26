package routes.user

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.log
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import models.dto.user.UserCreateDto
import models.dto.validate
import models.validation.user.UserValidation
import repository.UserRepository
import org.koin.ktor.ext.get

fun Application.userRoutes() {
    routing{
        authenticate("auth-jwt") {
            val userRepo = get<UserRepository>() // Получаем репозиторий из DI-контейнера

            get("/users") {
                val users = userRepo.getAll() // Получаем список всех пользователей
                call.respond(users) // Возвращаем результат в виде JSON
            }

            post("/create/user") {
                val dto = call.receive<UserCreateDto>()        // DTO из тела запроса

                val validator = UserValidation()               // Создаём валидатор
                val errors = validator.validateAll(dto)        // Собираем все ошибки

                if (errors.isNotEmpty()) {                     // Если есть ошибки — 400
                    call.respond(
                        HttpStatusCode.BadRequest,
                        mapOf("errors" to errors)
                    )
                    return@post
                }

                userRepo.add(dto)                             // Валидация прошла — пишем в БД
                call.respond(HttpStatusCode.Created, "User created")
                log.info("User created with username: '${dto.username}' and email: '${dto.email}'")
            }

            put("/change") {
                val userFromRequest = call.receive<UserCreateDto>()
                val userIdFromRequest = call.request.queryParameters["id"]
                val userId = userIdFromRequest?.toLongOrNull()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest, "Incorrect user id")
                    return@put
                }

                val changeUserData = userRepo.change(id = userId, update = userFromRequest)

                if (changeUserData) {
                    call.respond(HttpStatusCode.OK, "User is changed")
                } else call.respond(HttpStatusCode.NotFound, "User with id=$userId is not found")
            }
        }
    }
}