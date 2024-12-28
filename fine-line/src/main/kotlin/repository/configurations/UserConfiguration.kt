package repository.configurations

import org.jetbrains.exposed.sql.Table

object UserTable : Table("users") {
    val userId = varchar("userId", 50)
    val name = varchar("name", 50)

    override val primaryKey = PrimaryKey(userId)
}