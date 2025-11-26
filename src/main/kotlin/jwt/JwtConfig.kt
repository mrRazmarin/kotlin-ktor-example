package jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

// Класс для генерации и проверок JWT-токенов
class JwtConfig(
    secret: String,
    issuer: String,
    audience: String,
    realm: String
) {
    private val algorithm = Algorithm.HMAC256(secret)
    val issuer = issuer
    val audience = audience
    val realm = realm

    // Генерация нового токена для пользователя
    fun generateToken(username: String): String {
        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim("username", username)
            .sign(algorithm)
    }

    val verifier = JWT.require(algorithm)
        .withIssuer(issuer)
        .withAudience(audience)
        .build()
}