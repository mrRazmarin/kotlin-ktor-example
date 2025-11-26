package routes.Health

import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.healthRoutes() {
    routing {
        get("/health") {
            val checkStatus = try {
                transaction {
                    exec("SELECT 1") { it.next() }
                }
                "OK"
            } catch (e: Exception){
                "DB_ERROR: ${e.message}"
            }
            call.respondText("Health: $checkStatus")
        }
    }
}