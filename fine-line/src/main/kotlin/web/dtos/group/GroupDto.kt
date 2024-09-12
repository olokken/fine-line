package web.dtos.group

import domain.group.models.Group
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class GroupDto(val id: String, val name: String);

fun Group.toGroupDto(): GroupDto {
    return GroupDto(id = this.id.toString(), name = this.name)
}

