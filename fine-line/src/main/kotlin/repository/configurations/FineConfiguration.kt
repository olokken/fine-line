package repository.configurations

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object FineTable : Table("fine") {
    val fineId = integer("fineId").autoIncrement()
    val groupId = integer("groupId").references(GroupTable.groupId, onDelete = ReferenceOption.CASCADE)

    val receiverId = varchar("reciever", 50).references(UserTable.userId, onDelete = ReferenceOption.CASCADE)
    val issuerId = varchar("issuer", 50).references(UserTable.userId, onDelete = ReferenceOption.SET_NULL)

    val fineTypeId = integer("fineTypeId").references(FineTypeTable.fineTypeId, onDelete = ReferenceOption.CASCADE)
    val timeIssued = datetime("timeIssued")
    override val primaryKey = PrimaryKey(fineId, name = "pk_fine")
}