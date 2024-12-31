package repository.membership

import domain.membership.models.Membership
import org.jetbrains.exposed.sql.ResultRow
import repository.configurations.UserGroupTable

fun mapToMembership(row: ResultRow): Membership {
    return Membership(
        userId = row[UserGroupTable.userId],
        groupId = row[UserGroupTable.groupId],
        isAdmin = row[UserGroupTable.isAdmin],
        status = row[UserGroupTable.status]
    )
}