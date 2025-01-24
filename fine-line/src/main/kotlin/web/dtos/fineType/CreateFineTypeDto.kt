package web.dtos.fineType

import domain.fineType.models.FineTypeCreateModel
import kotlinx.serialization.Serializable

@Serializable
data class CreateFineTypeDto(val name: String, val description: String, val sum: Double, val groupId: Int)

fun CreateFineTypeDto.toCreateModel(): FineTypeCreateModel {
    return FineTypeCreateModel(
        name = name,
        description = description,
        sum = sum,
        groupId = groupId
    )
}