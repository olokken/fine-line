package web.dtos.user

import domain.user.models.User
import kotlinx.serialization.Serializable
import web.dtos.group.GroupDto
import web.dtos.group.toGroupDto

@Serializable
data class UserDetailDto(val userId: String, val name: String, val groups: List<GroupDto>)

fun User.toUserDetailDto(): UserDetailDto {
    return UserDetailDto(this.userId, this.name, this.groups.map { group -> group.toGroupDto() })
}