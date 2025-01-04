package web.dtos.user

import domain.user.models.User
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(val userId: String, val name: String)

fun User.toUserDto(): UserDto {
    return UserDto(this.userId, this.name)
}
