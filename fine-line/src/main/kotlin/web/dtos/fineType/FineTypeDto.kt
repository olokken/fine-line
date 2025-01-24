package web.dtos.fineType

import domain.fineType.models.FineType
import kotlinx.serialization.Serializable

@Serializable
data class FineTypeDto(val fineTypeId: Int, val name: String, val description: String, val sum: Double)

fun FineType.toDto(): FineTypeDto {
    return FineTypeDto(
        fineTypeId = fineTypeId,
        name = name,
        description = description,
        sum = sum,
    )
}
