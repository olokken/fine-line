package web.controllers

import common.either.foldSuspend
import domain.fine.FineService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import web.dtos.fine.CreateFineDto
import web.dtos.fine.toModel


class FineController(private val fineService: FineService) : BaseController() {
    fun setUpRoutes(route: Route) {
        route.route("/api/v1/groups/fines") {
            post {
                createFine(call)
            }
        }
    }

    suspend fun createFine(call: ApplicationCall) {
        val dto = call.receive<CreateFineDto>();
        val issuerId = getUserIdFromToken(call)

        if (issuerId == null) return call.respondText(
            status = HttpStatusCode.fromValue(403),
            text = "Invalid token"
        )

        fineService.createFine(dto.toModel(issuerId)).foldSuspend({ error ->
            call.respondText(
                status = HttpStatusCode.fromValue(error.statusCode),
                text = error.message
            )
        }, { id ->
            call.respond(status = HttpStatusCode.Created, id)
        })
    }
}