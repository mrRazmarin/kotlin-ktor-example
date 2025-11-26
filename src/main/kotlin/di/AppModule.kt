package di

import config.AppConfig
import db.DatabaseFactory
import org.koin.dsl.module
import repository.UserRepository
import repository.UserRepositoryImpl

val appModule = module {
    single { AppConfig(get()) } // Конфиг приложения через DI
    single<UserRepository> { UserRepositoryImpl() } // интерфейс через реализацию
    single { DatabaseFactory } // Singleton для работы с базой
}