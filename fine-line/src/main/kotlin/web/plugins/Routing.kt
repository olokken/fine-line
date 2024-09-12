package web.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import web.controllers.GroupController

fun Application.configureRouting() {
    val groupController: GroupController by inject();

    routing {
        groupController.setUpRoutes(this);
    }
}
