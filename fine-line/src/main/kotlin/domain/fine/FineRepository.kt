package domain.fine

import common.either.Either
import common.error.RepositoryError
import domain.fine.models.FineCreateModel

interface FineRepository {
    fun createFine(createModel: FineCreateModel): Either<RepositoryError, Int>
}