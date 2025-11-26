import config.AppConfig
import db.DatabaseFactory
import di.appModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.auth.Authentication
import io.ktor.server.netty.EngineMain
import routes.Health.healthRoutes
import io.ktor.server.auth.jwt.*
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import jwt.JwtConfig

import org.koin.ktor.ext.get
import org.koin.ktor.plugin.Koin
import routes.auth.authRoutes
import routes.user.userRoutes


fun main(args: Array<String>) {
    EngineMain.main(args) // Точка входа приложения
}


fun Application.module() {
    // Подключение Koin: инициализация DI контейнера и модулей
    install(Koin) {
        modules(appModule)
    }

    // Дальше получаем все зависимости через DI, например:
    val config = get<AppConfig>()
    val dbFactory = get <DatabaseFactory>()
    val jwtConfig = JwtConfig(
        secret = config.jwtRealm,
        issuer = config.jwtIssuer,
        audience = config.jwtAudience,
        realm = config.jwtRealm
    )

    dbFactory.init(config)

    install(Authentication) {
        jwt("auth-jwt") {
            realm = jwtConfig.realm
            verifier(jwtConfig.verifier)
            validate { credential ->
                if (credential.payload.getClaim("username").asString().isNotBlank())
                    JWTPrincipal(credential.payload)
                else null
            }
        }
    }

    routing {
        route("/api/v1") {
            healthRoutes()
            authRoutes()
            userRoutes()
        }
    }


    log.info("Ktor server started with db: ${config.jdbcUrl}, user: ${config.dbUser}")
}
