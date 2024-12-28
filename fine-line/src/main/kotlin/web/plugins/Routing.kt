package web.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import web.controllers.GroupController
import web.controllers.UserController

fun Application.configureRouting() {
    val groupController: GroupController by inject()
    val userController: UserController by inject()

    routing {
        authenticate("keycloak-bearer") {
            groupController.setUpRoutes(this)
            userController.setUpRoutes(this)
        }
    }
}
