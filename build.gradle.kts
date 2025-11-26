plugins {
    kotlin("jvm") version "2.2.20"
    application
}

val ktor_version = "3.3.2" // Версия фреймворка Ktor
val kotlin_version = "2.2.20" // Версия Kotlin - актуальная на 13 ноября 2025г.
val logback_version = "1.5.21" // Логгер logback
val exposed_version = "0.61.0" // ORM Exposed для работы с PGSql
val koin_version = "4.1.1" // Koin для DI
val postgres_version = "42.7.8" // JDBC драйвер PG
val jwt_version = "4.5.0" // Java JWT для работы с токенами


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Базовое ядро Ktor сервера
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    // Netty – HTTP‑сервер, на котором будет крутиться Ktor
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")

    // Логирование через Logback (дефолтный логгер для Ktor)
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Модули Exposed: core, DAO и JDBC – для описания таблиц и запросов к PostgreSQL
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")

    // JDBC-драйвер PostgreSQL – без него не подключимся к базе
    implementation("org.postgresql:postgresql:$postgres_version")

    // Koin интеграция с Ktor – для DI в хендлерах, сервисах и т.п.
    implementation("io.insert-koin:koin-ktor:$koin_version")
    // Логирование внутри Koin
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")

    // Java JWT – генерация и валидация JWT токенов для аутентификации
    implementation("com.auth0:java-jwt:$jwt_version")

    // Модуль авторизации Ktor
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    // Модуль JWT для Ktor (работа с jwt-токенами)
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktor_version")

    // Для работы с .env-файлом
    implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")

    // Тестовые зависимости для Ktor
    testImplementation("io.ktor:ktor-server-tests-jvm:${ktor_version}")
    // Kotlin тестовый раннер (JUnit)
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${kotlin_version}")
    testImplementation("io.ktor:ktor-server-test-host-jvm:3.3.2")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

kotlin {
    jvmToolchain(11)
}