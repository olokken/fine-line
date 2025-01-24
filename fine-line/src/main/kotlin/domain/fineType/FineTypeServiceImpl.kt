package domain.fineType

import common.either.Either
import common.either.mapLeft
import common.either.mapRight
import common.error.ErrorResponse
import domain.fineType.models.FineType
import domain.fineType.models.FineTypeCreateModel

class FineTypeServiceImpl(private val fineTypeRepository: FineTypeRepository) : FineTypeService {
    override fun createFineType(createModel: FineTypeCreateModel, userId: String): Either<ErrorResponse, FineType> {

        return fineTypeRepository.createFineType(createModel)
            .mapLeft { error -> ErrorResponse(500, "Unknown error creating Fine type") }
            .mapRight { id -> FineType(id, createModel.name, createModel.description, createModel.sum) }
    }
}