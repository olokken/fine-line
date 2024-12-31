package domain.membership.models

import kotlinx.serialization.Serializable

@Serializable
data class Membership(val userId: String, val groupId: Int, val isAdmin: Boolean, val status: MembershipStatus)
