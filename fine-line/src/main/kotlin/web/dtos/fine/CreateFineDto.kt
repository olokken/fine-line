package web.dtos.fine

import domain.fine.models.FineCreateModel
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class CreateFineDto(val groupId: Int, val receiverId: String, val fineTypeId: Int)

fun CreateFineDto.toModel(issuerId: String): FineCreateModel {
    return FineCreateModel(
        groupId = this.groupId,
        receiverId = this.receiverId,
        fineTypeId = this.fineTypeId,
        issuerId = issuerId,
        timeIssued = LocalDateTime.now(),
    )
}
