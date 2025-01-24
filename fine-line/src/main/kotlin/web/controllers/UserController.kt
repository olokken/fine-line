package web.controllers

import com.auth0.jwt.JWT
import common.either.foldSuspend
import domain.user.models.UserCreateModel
import domain.user.models.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.*
import web.dtos.user.toUserDetailDto

class UserController(private val userService: UserService) {
    fun setUpRoutes(route: Route) {
        route.route("/api/v1/users") {
            post {
                createOrGetUser(call)
            }
        }
    }

    suspend fun createOrGetUser(call: ApplicationCall) {
        val user = mapUserFromToken(call)

        if (user == null) return call.respondText(
            status = HttpStatusCode.fromValue(403),
            text = "Invalid token"
        )

        userService.createOrGetUser(user)
            .foldSuspend(
                { error ->
                    call.respondText(
                        status = HttpStatusCode.fromValue(error.statusCode),
                        text = error.message
                    )
                },
                { user -> call.respond(user.toUserDetailDto()) }
            )
    }

    private fun mapUserFromToken(call: ApplicationCall): UserCreateModel? {
        val header = call.request.headers["Authorization"]
        if (header == null) return null

        val token = header.removePrefix("Bearer ")

        val jwt = JWT.decode(token)
        val userId = jwt.getClaim("sub").asString()
        val userName = jwt.getClaim("preferred_username").asString()

        return UserCreateModel(userId, userName)
    }
}