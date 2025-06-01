package domain.fineType

import common.either.Either
import common.error.ErrorResponse
import domain.fineType.models.FineType
import domain.fineType.models.FineTypeCreateModel

interface FineTypeService {
    fun createFineType(createModel: FineTypeCreateModel, userId: String): Either<ErrorResponse, FineType>
    fun isFineTypeInGroup(groupId: Int, fineTypeId: Int): Either<ErrorResponse, Boolean>;
}