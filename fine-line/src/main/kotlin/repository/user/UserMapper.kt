package repository.user

import domain.group.models.Group
import domain.user.models.User
import org.jetbrains.exposed.sql.ResultRow
import repository.configurations.UserTable

fun mapToUser(row: ResultRow, groups: List<Group>): User {
    return User(
        userId = row[UserTable.userId],
        name = row[UserTable.name],
        groups = groups
    )
}