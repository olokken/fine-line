package web.plugins

import domain.group.GroupRepository
import domain.group.GroupService
import domain.group.GroupServiceImpl
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import web.controllers.GroupController
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import repository.group.GroupRepositoryImpl

fun Application.configureFrameworks() {
    install(Koin) {
        modules(module {
            single<GroupService> { GroupServiceImpl(get()) }
            single<GroupRepository> { GroupRepositoryImpl() }
            single { GroupController(get()) }
        })
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}
