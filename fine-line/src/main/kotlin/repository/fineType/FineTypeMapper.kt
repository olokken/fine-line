package repository.fineType

import domain.fineType.models.FineType
import org.jetbrains.exposed.sql.ResultRow
import repository.configurations.FineTypeTable

fun mapToFineType(row: ResultRow): FineType {
    return FineType(
        fineTypeId = row[FineTypeTable.fineTypeId],
        name = row[FineTypeTable.name],
        description = row[FineTypeTable.description],
        sum = row[FineTypeTable.sum],
    )
}