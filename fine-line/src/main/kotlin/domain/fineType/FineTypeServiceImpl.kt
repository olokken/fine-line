package domain.fineType

import common.either.Either
import common.either.flatMap
import common.either.mapLeft
import common.either.mapRight
import common.error.ErrorResponse
import domain.fineType.models.FineType
import domain.fineType.models.FineTypeCreateModel
import domain.membership.MembershipService

class FineTypeServiceImpl(
    private val fineTypeRepository: FineTypeRepository,
    private val membershipService: MembershipService
) : FineTypeService {
    override fun createFineType(createModel: FineTypeCreateModel, userId: String): Either<ErrorResponse, FineType> {
        return membershipService.isUserAdmin(createModel.groupId, userId)
            .mapLeft { errorResponse -> ErrorResponse(500, "Failed to check if user is admin") }
            .flatMap { isAdmin ->
                if (!isAdmin) return@flatMap Either.Left(ErrorResponse(403, "User is not admin"))

                fineTypeRepository.createFineType(createModel)
                    .mapLeft { error -> ErrorResponse(500, "Unknown error creating Fine type") }
                    .mapRight { id -> FineType(id, createModel.name, createModel.description, createModel.sum) }
            }
    }

    override fun isFineTypeInGroup(
        groupId: Int,
        fineTypeId: Int
    ): Either<ErrorResponse, Boolean> {
        return fineTypeRepository.isFineTypeInGroup(groupId, fineTypeId)
            .mapLeft { errorResponse -> ErrorResponse(500, "Failed to check if fine type is in group") }

    }
}