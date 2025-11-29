package di

import config.AppConfig
import db.DatabaseFactory
import models.validation.AbstractValidator
import models.validation.register.RegisterValidation
import models.validation.register.RegisterValidationImpl
import org.koin.dsl.module
import repository.register.RegisterRepository
import repository.register.RegisterRepositoryImpl
import repository.user.UserRepository
import repository.user.UserRepositoryImpl

val appModule = module {
    single { AppConfig(get()) } // Конфиг приложения через DI
    single <UserRepository> { UserRepositoryImpl() } // интерфейс через реализацию
    single { DatabaseFactory } // Singleton для работы с базой
    single <RegisterRepository> { RegisterRepositoryImpl() } // Добавляем репу в DI
    single <RegisterValidation> { RegisterValidationImpl() }
}