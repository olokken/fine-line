package web.dtos.group

import domain.group.models.GroupCreateModel
import kotlinx.serialization.Serializable

@Serializable
data class GroupCreateDto(val name: String)

fun GroupCreateDto.toModel(creatorId: String): GroupCreateModel {
    return GroupCreateModel(name = this.name, creatorId = creatorId);
}
