package repository.group

import domain.group.models.Group
import domain.user.models.User
import org.jetbrains.exposed.sql.ResultRow
import repository.configurations.GroupTable
import domain.membership.models.MembershipStatus
import repository.configurations.UserGroupTable
import repository.configurations.UserTable

data class UserInGroup(
    val userId: String,
    val name: String,
    val groupId: Int,
    val isAdmin: Boolean,
    val status: MembershipStatus
)

fun UserInGroup.toUser(): User {
    return User(this.userId, this.name, emptyList())
}


fun mapToGroup(row: ResultRow, users: List<UserInGroup>): Group {
    return Group(
        groupId = row[GroupTable.groupId],
        name = row[GroupTable.name],
        admins = users.filter { u -> u.status == MembershipStatus.Accepted && u.isAdmin == true }.map { it.toUser() },
        members = users.filter { u -> u.status == MembershipStatus.Accepted && u.isAdmin == false }.map { it.toUser() },
        pendingMembers = users.filter { u -> u.status == MembershipStatus.Requested }.map { it.toUser() }
    )
}

fun mapToUserInGroup(row: ResultRow): UserInGroup {
    return UserInGroup(
        userId = row[UserGroupTable.userId],
        name = row[UserTable.name],
        groupId = row[UserGroupTable.groupId],
        isAdmin = row[UserGroupTable.isAdmin],
        status = row[UserGroupTable.status]
    )
}


