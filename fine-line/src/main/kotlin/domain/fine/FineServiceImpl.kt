package domain.fine

import common.either.Either
import common.either.flatMap
import common.either.mapLeft
import common.error.ErrorResponse
import domain.fine.models.FineCreateModel
import domain.fineType.FineTypeService
import domain.membership.MembershipService

class FineServiceImpl(
    private val fineRepository: FineRepository,
    private val membershipService: MembershipService,
    private val fineTypeService: FineTypeService
) : FineService {
    override fun createFine(createModel: FineCreateModel): Either<ErrorResponse, Int> {
        return membershipService.isUserAdmin(createModel.groupId, createModel.issuerId)
            .mapLeft { error -> ErrorResponse(500, "Failed to check if issuer is admin") }
            .flatMap { isAdmin ->
                if(!isAdmin) return@flatMap Either.Left(ErrorResponse(403, "User is not admin"));

                fineTypeService.isFineTypeInGroup(createModel.groupId, createModel.fineTypeId)
                    .mapLeft { error -> ErrorResponse(500, "Failed to check if issuer is admin") }
                    .flatMap { isFineTypeInGroup ->
                        if(!isFineTypeInGroup) return@flatMap Either.Left(ErrorResponse(404, "Cannot find fine type"))

                        fineRepository.createFine(createModel)
                            .mapLeft { error -> ErrorResponse(500, "Failed to create fine") }
                    }
            }
    }
}