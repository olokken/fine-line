package repository.configurations

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object FineTypeTable : Table("fineType") {
    val fineTypeId = integer("fineTypeId").autoIncrement()
    val name = varchar("name", 50)
    val description = varchar("description", 300)
    val sum = double("sum")
    val groupId = integer("groupId").references(GroupTable.groupId, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(fineTypeId, name = "pk_fineType")
}