package web.controllers

import common.either.foldSuspend
import domain.group.GroupService
import domain.group.models.GroupCreateModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import web.dtos.group.GroupCreateDto
import web.dtos.group.GroupDto
import web.dtos.group.toDetailDto
import web.dtos.group.toGroupDto
import web.dtos.group.toModel
import web.utils.getUserIdFromToken

class GroupController(private val groupService: GroupService) {
    fun setUpRoutes(route: Route) {
        route.route("/api/v1/groups") {
            get {
                getGroups(call);
            }

            post {
                createGroup(call);
            }

            get("{id}") {
                getGroup(call)
            }
        }
    }

    suspend fun createGroup(call: ApplicationCall) {
        val dto = call.receive<GroupCreateDto>();
        val userId = getUserIdFromToken(call)

        if (userId == null) return call.respondText(
            status = HttpStatusCode.fromValue(403),
            text = "Invalid token"
        )

        val createGroupModel: GroupCreateModel = dto.toModel(userId)
        groupService.createGroup(createGroupModel).foldSuspend({ error ->
            call.respondText(
                status = HttpStatusCode.fromValue(error.statusCode),
                text = error.message
            )
        }, { group -> call.respond(group.toGroupDto()) })
    }

    suspend fun getGroups(call: ApplicationCall) {
        val groups = groupService.getAllGroups();
        val groupDtos = groups.map { group -> group.toGroupDto() }.toList();
        return call.respond<List<GroupDto>>(groupDtos);
    }

    suspend fun getGroup(call: ApplicationCall) {
        val idParam = call.parameters["id"]
        val id = idParam?.toIntOrNull()

        if (id == null) {
            return call.respondText(status = HttpStatusCode.fromValue(404), text = "Group not found")
        }

        groupService.getGroup(id)
            .foldSuspend({ error ->
                call.respondText(
                    status = HttpStatusCode.fromValue(error.statusCode),
                    text = error.message
                )
            },
                { group -> call.respond(group.toDetailDto()) })
    }
}
