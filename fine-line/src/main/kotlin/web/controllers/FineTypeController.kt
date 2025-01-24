package web.controllers

import common.either.foldSuspend
import domain.fineType.FineTypeService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import web.dtos.fineType.CreateFineTypeDto
import web.dtos.fineType.toCreateModel
import web.dtos.fineType.toDto
import web.utils.getUserIdFromToken

class FineTypeController(private val fineTypeService: FineTypeService) {
    fun setUpRoutes(route: Route) {
        route.route("/api/v1/groups/fineTypes") {
            post {
                createFineType(call)
            }
        }
    }

    suspend fun createFineType(call: ApplicationCall) {
        val dto = call.receive<CreateFineTypeDto>()
        val userId = getUserIdFromToken(call)

        if (userId == null) return call.respondText(
            status = HttpStatusCode.fromValue(403),
            text = "Invalid token"
        )

        fineTypeService.createFineType(dto.toCreateModel(), userId)
            .foldSuspend({error ->
                call.respondText(
                    status = HttpStatusCode.fromValue(error.statusCode),
                    text = error.message
                )
            }, {
                fineType -> call.respond(fineType.toDto())
            })
    }
}