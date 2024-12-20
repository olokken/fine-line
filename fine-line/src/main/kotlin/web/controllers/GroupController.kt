package web.controllers

import domain.group.GroupService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import web.dtos.group.GroupCreateDto
import web.dtos.group.GroupDto
import web.dtos.group.toGroupDto
import web.dtos.group.toModel

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
        val group = groupService.createGroup(dto.toModel());
        return call.respond(group.toGroupDto());
    }

    suspend fun getGroups(call: ApplicationCall) {
        val groups = groupService.getAllGroups();
        val groupDtos = groups.map { group -> group.toGroupDto() }.toList();
        return call.respond<List<GroupDto>>(groupDtos);
    }
}
