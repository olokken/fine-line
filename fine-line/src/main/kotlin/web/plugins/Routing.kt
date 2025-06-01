package web.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import web.controllers.*

fun Application.configureRouting() {
    val groupController: GroupController by inject()
    val userController: UserController by inject()
    val membershipController: MembershipController by inject()
    val fineTypeController: FineTypeController by inject()
    val fineController: FineController by inject()

    routing {
        authenticate("keycloak-bearer") {
            groupController.setUpRoutes(this)
            userController.setUpRoutes(this)
            membershipController.setUpRoutes(this)
            fineTypeController.setUpRoutes(this)
            fineController.setUpRoutes(this)
        }
    }
}
