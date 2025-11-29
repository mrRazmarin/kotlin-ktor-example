package routes.register

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import models.dto.ErrorDto
import models.dto.ErrorsResponseDto
import models.dto.register.RegisterRequest
import models.dto.register.RegisterResponse
import models.validation.register.RegisterValidation
import repository.register.RegisterRepository
import org.koin.ktor.ext.get

fun Application.registerRoutes() {

    val registerRepo = get<RegisterRepository>()
    val registerValidation = get<RegisterValidation>()

    routing {
        post("/register") {

            val dto = call.receive<RegisterRequest>() // DTO из тела запроса
            val errors = registerValidation.validateAll(dto)

            if (errors.isNotEmpty()){
                call.respond(HttpStatusCode.BadRequest,
                    ErrorsResponseDto(errors.map { ErrorDto(it) })
                )
                return@post
            }
            if (registerValidation.hasUser(dto)) {
                call.respond(
                    HttpStatusCode.Conflict,
                    RegisterResponse(
                        "User with username = ${dto.username} and ${dto.email} is already exists"
                    )
                )
                return@post
            }
            else {
                val userId = registerRepo.create(dto)
                call.respond(HttpStatusCode.Created, RegisterResponse("User with id = $userId is registered"))
                return@post
            }
        }
    }
}