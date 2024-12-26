package domain.membership

import common.either.Either
import common.either.mapLeft
import common.error.DatabaseExceptionType
import common.error.ErrorResponse

class MembershipServiceImpl(private val membershipRepository: MembershipRepository) : MembershipService {
    override fun addMember(userId: String, groupId: Int, isAdmin: Boolean): Either<ErrorResponse, Boolean> {
        return membershipRepository.addMember(userId, groupId, isAdmin)
            .mapLeft { error ->
                when (error.exceptionType) {
                    DatabaseExceptionType.UniqueConstraintViolation ->
                        ErrorResponse(400, "User is already a member")
                    DatabaseExceptionType.ForeignKeyConstraintViolation ->
                        ErrorResponse(400, "User or Group doesn't exist")
                    else ->
                        ErrorResponse(500, "Unknown database error")
                }
            }
    }
}