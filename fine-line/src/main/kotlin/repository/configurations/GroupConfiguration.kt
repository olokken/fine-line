package repository.configurations

import org.jetbrains.exposed.sql.Table

object GroupTable : Table("groups") {
    val groupId = integer("groupId").autoIncrement()
    val name = varchar("name", 50).uniqueIndex()

    override val primaryKey = PrimaryKey(groupId, name = "pk_group")
}