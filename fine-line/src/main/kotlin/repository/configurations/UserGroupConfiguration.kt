package repository.configurations

import domain.membership.models.MembershipStatus
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object UserGroupTable : Table("users_groups") {
    val userId = varchar("userId", 50).references(UserTable.userId, onDelete = ReferenceOption.CASCADE)
    val groupId = integer("groupId").references(GroupTable.groupId, onDelete = ReferenceOption.CASCADE)
    val status = enumerationByName("status", 50, MembershipStatus::class).default(MembershipStatus.Accepted)
    val isAdmin = bool("isAdmin");

    override val primaryKey = PrimaryKey(groupId, userId, name = "pk_users_groups")
}