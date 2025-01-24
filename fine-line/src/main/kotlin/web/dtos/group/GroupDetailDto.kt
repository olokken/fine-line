package web.dtos.group

import domain.group.models.Group
import kotlinx.serialization.Serializable
import web.dtos.fineType.FineTypeDto
import web.dtos.fineType.toDto
import web.dtos.user.UserDto
import web.dtos.user.toUserDto

@Serializable
data class GroupDetailDto(
    val groupId: Int,
    val name: String,
    val fineTypes: List<FineTypeDto>,
    val admins: List<UserDto>,
    val members: List<UserDto>,
    val pendingMembers: List<UserDto>
)

fun Group.toDetailDto(): GroupDetailDto {
    return GroupDetailDto(
        groupId = this.groupId,
        name = this.name,
        admins = admins.map { user -> user.toUserDto() },
        members = members.map { user -> user.toUserDto() },
        pendingMembers = pendingMembers.map { user -> user.toUserDto() },
        fineTypes = fineTypes.map { fineType -> fineType.toDto() }
    )
}
