package domain.group.models

import domain.fineType.models.FineType
import domain.user.models.User

data class Group(
    val groupId: Int,
    val name: String,
    val admins: List<User>,
    val members: List<User>,
    val pendingMembers: List<User>,
    val fineTypes: List<FineType>
)
