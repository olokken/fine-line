package web.controllers

import common.either.foldSuspend
import domain.membership.MembershipService
import domain.membership.models.Membership
import domain.membership.models.MembershipStatus
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
import web.dtos.membership.UpdateRequestedMembershipDto
import web.utils.getUserIdFromToken

class MembershipController(private val membershipService: MembershipService) {
    fun setUpRoutes(route: Route) {
        route.route("/api/v1/membership") {
            post {
                requestMembership(call)
            }

            put {
                updateMembershipStatus(call)
            }
        }
    }

    suspend fun updateMembershipStatus(call: ApplicationCall) {
        val dto = call.receive<UpdateRequestedMembershipDto>()
        val adminId = getUserIdFromToken(call)

        if (adminId == null) {
            return call.respond(HttpStatusCode.Unauthorized, "Unauthorized request")
        }

        val status = if (dto.accepted) MembershipStatus.Accepted else MembershipStatus.Rejected;

        val memberShip = Membership(dto.userId, dto.groupId, false, status)

        membershipService.updateMembership(memberShip, adminId).foldSuspend({ error ->
                call.respondText(
                    status = HttpStatusCode.fromValue(error.statusCode), text = error.message
                )
            }, { memberShip ->
                call.respond(memberShip)
            })
    }

    suspend fun requestMembership(call: ApplicationCall) {
        val dto = call.receive<RequestMembershipDto>()
        val userId = getUserIdFromToken(call)

        if (dto.userId != userId) {
            return call.respondText(
                status = HttpStatusCode.fromValue(403), text = "Only allowed to request your own membership"
            )
        }

        membershipService.requestMembership(dto.userId, dto.groupId).foldSuspend({ error ->
                call.respondText(
                    status = HttpStatusCode.fromValue(error.statusCode), text = error.message
                )
            }, { membership ->
                call.respond(membership)
            })
    }

}