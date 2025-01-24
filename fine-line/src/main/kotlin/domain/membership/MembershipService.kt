package domain.membership

import common.either.Either
import common.error.ErrorResponse
import domain.membership.models.Membership

interface MembershipService {
    fun addMembership(membership: Membership): Either<ErrorResponse, Boolean>
    fun requestMembership(userId: String, groupId: Int): Either<ErrorResponse, Membership>
    fun updateMembership(membership: Membership, adminId: String): Either<ErrorResponse, Membership>
    fun isUserAdmin(groupId: Int, userId: String): Either<ErrorResponse, Boolean>
    fun isUserAcceptedMember(groupId: Int, userId: String): Either<ErrorResponse, Boolean>
}