package web.dtos.group

import domain.group.models.Group
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class GroupDto(val groupId: Int, val name: String);

fun Group.toGroupDto(): GroupDto {
    return GroupDto(groupId = this.groupId, name = this.name)
}
