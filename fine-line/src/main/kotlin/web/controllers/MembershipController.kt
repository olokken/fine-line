package web.controllers

import common.either.foldSuspend
import domain.membership.MembershipService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import web.dtos.membership.RequestMembershipDto
import web.utils.getUserIdFromToken

class MembershipController(private val membershipService: MembershipService) {
    fun setUpRoutes(route: Route) {
        route.route("/api/v1/membership") {
            post {
                requestMembership(call)
            }

            put {
                updateMembership(call)
            }
        }
    }

    suspend fun updateMembership(call: ApplicationCall) {
        
    }

    suspend fun requestMembership(call: ApplicationCall) {
        val dto = call.receive<RequestMembershipDto>()
        val userId = getUserIdFromToken(call)

        if (dto.userId != userId) {
            return call.respondText(
                status = HttpStatusCode.fromValue(403),
                text = "Only allowed to request your own membership"
            )
        }

        membershipService.requestMembership(dto.userId, dto.groupId)
            .foldSuspend({ error ->
                call.respondText(
                    status = HttpStatusCode.fromValue(error.statusCode),
                    text = error.message
                )
            }, { membership ->
                call.respond(membership)
            })
    }

}