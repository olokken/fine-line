package domain.fine.models

import domain.fineType.models.FineType
import domain.user.models.User
import java.time.LocalDateTime

data class Fine(val fineId: Int, val fineType: FineType, val receiver: User, val issuer: User, val timeIssued: LocalDateTime)