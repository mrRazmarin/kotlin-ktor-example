package routes

import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class HealthRoutesKtTest {

    @Test
    fun testGetHealth() = testApplication {
        application {
            TODO("Add the Ktor module for the test")
        }
        client.get("/health").apply {
            TODO("Please write your test here")
        }
    }
}