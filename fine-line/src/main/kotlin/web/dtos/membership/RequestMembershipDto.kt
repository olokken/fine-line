package web.dtos.membership

import kotlinx.serialization.Serializable

@Serializable
data class RequestMembershipDto(val userId: String, val groupId: Int)
