package domain.membership

import common.either.Either
import common.error.ErrorResponse

interface MembershipService {
    fun addMember(userId: String, groupId: Int, isAdmin: Boolean): Either<ErrorResponse, Boolean>;
}