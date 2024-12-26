package repository.configurations

import org.jetbrains.exposed.sql.Table

object UserGroupTable : Table("users_groups") {
    val userId = varchar("userId", 50)
    val groupId = integer("groupId")
    val isAdmin = bool("isAdmin");

    override val primaryKey = PrimaryKey(groupId, userId, name = "pk_users_groups")
}