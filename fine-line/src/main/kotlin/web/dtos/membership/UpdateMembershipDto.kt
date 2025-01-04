package web.dtos.membership

import kotlinx.serialization.Serializable

@Serializable
data class UpdateMembershipDto(val groupId: Int, val userId: String, val accepted: Boolean)
