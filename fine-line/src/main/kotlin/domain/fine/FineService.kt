package domain.fine

import common.either.Either
import common.error.ErrorResponse
import domain.fine.models.FineCreateModel

interface FineService {
    fun createFine(createModel: FineCreateModel): Either<ErrorResponse, Int>
}