package web.dtos.user

import domain.user.models.User
import kotlinx.serialization.Serializable
import web.dtos.group.GroupDto
import web.dtos.group.toGroupDto

@Serializable
data class UserDto(val userId: String, val name: String, val groups: List<GroupDto>)

fun User.toUserDto(): UserDto {
    return UserDto(this.userId, this.name, this.groups.map { group -> group.toGroupDto() })
}
