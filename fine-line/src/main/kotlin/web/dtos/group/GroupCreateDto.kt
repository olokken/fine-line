package web.dtos.group

import domain.group.models.Group
import domain.group.models.GroupCreateModel
import kotlinx.serialization.Serializable

@Serializable
data class GroupCreateDto(val name: String)

fun GroupCreateDto.toModel(): GroupCreateModel {
    return GroupCreateModel(name = this.name);
}
