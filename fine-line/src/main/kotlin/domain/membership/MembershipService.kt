package domain.membership

import common.either.Either
import common.error.ErrorResponse
import domain.membership.models.Membership

interface MembershipService {
    fun addMembership(membership: Membership): Either<ErrorResponse, Boolean>;
    fun requestMembership(userId: String, groupId: Int): Either<ErrorResponse, Membership>
    fun acceptMembership(userId: String, groupId: Int): Either<ErrorResponse, Membership>
}