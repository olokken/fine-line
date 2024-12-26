package repository.group

import domain.group.models.Group
import org.jetbrains.exposed.sql.ResultRow
import repository.configurations.GroupTable

fun mapToGroup(row: ResultRow): Group {
    return Group(
        groupId = row[GroupTable.groupId],
        name = row[GroupTable.name],
        admins = emptyList()
    )
}
