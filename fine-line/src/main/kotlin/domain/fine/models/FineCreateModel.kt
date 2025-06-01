package domain.fine.models

import java.time.LocalDateTime

data class FineCreateModel(val groupId: Int, val fineTypeId: Int, val receiverId: String, val issuerId: String, val timeIssued: LocalDateTime)