package domain.fineType

import common.either.Either
import common.error.RepositoryError
import domain.fineType.models.FineTypeCreateModel

interface FineTypeRepository {
    fun createFineType(createModel: FineTypeCreateModel): Either<RepositoryError, Int>
    fun isFineTypeInGroup(groupId: Int, fineTypeId: Int): Either<RepositoryError, Boolean>;
}