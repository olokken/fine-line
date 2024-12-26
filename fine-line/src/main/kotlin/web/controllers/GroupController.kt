package web.controllers

import common.either.Either
import common.either.fold
import common.either.foldSuspend
import common.either.left
import common.either.mapLeft
import common.either.mapRight
import common.either.right
import domain.group.GroupService
import domain.group.models.GroupCreateModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import web.dtos.group.GroupCreateDto
import web.dtos.group.GroupDto
import web.dtos.group.toGroupDto
import web.dtos.group.toModel
import java.awt.TrayIcon

class GroupController(private val groupService: GroupService) {
    fun setUpRoutes(route: Route) {
        route.route("/api/v1/groups") {
            get {
                getGroups(call);
            }

            post {
                createGroup(call);
            }
        }
    }

    suspend fun createGroup(call: ApplicationCall) {
        val dto = call.receive<GroupCreateDto>();
        val createGroupModel: GroupCreateModel = dto.toModel("siiu")
        groupService.createGroup(createGroupModel).foldSuspend({ error ->
            call.respondText(
                status = HttpStatusCode.fromValue(error.statusCode),
                text = error.message
            )
        }, { group -> call.respond(group) })
    }

    suspend fun getGroups(call: ApplicationCall) {
        val groups = groupService.getAllGroups();
        val groupDtos = groups.map { group -> group.toGroupDto() }.toList();
        return call.respond<List<GroupDto>>(groupDtos);
    }
}
