package domain.membership

import common.either.Either
import common.either.flatMap
import common.either.mapLeft
import common.either.mapRight
import common.error.DatabaseExceptionType
import common.error.ErrorResponse
import domain.membership.models.Membership
import domain.membership.models.MembershipStatus

class MembershipServiceImpl(private val membershipRepository: MembershipRepository) : MembershipService {
    override fun addMembership(membership: Membership): Either<ErrorResponse, Boolean> {
        return membershipRepository.addMembership(membership)
            .mapLeft { error ->
                when (error.exceptionType) {
                    DatabaseExceptionType.UniqueConstraintViolation ->
                        ErrorResponse(400, "User is already a member or has requested membership")

                    DatabaseExceptionType.ForeignKeyConstraintViolation ->
                        ErrorResponse(400, "User or Group doesn't exist")

                    else ->
                        ErrorResponse(500, "Unknown database error")
                }
            }
    }

    override fun requestMembership(userId: String, groupId: Int): Either<ErrorResponse, Membership> {
        return membershipRepository.getMembership(userId, groupId)
            .mapLeft { error -> ErrorResponse(500, "Unknown error") }
            .flatMap { membership ->
                if (membership != null) {
                    return@flatMap Either.Left(ErrorResponse(409, "Membership already requested"))
                }

                val newMembership = Membership(userId, groupId, false, MembershipStatus.Requested)
                membershipRepository.addMembership(newMembership)
                    .mapLeft { error -> ErrorResponse(500, "Failed to request membership") }
                    .mapRight { newMembership }
            }
    }

    override fun updateMembership(membership: Membership, adminId: String): Either<ErrorResponse, Membership> {
        return isUserAdmin(membership.groupId, adminId)
            .mapLeft { error -> ErrorResponse(500, "Failed to check if user is admin") }
            .flatMap { isAdmin ->
                if (isAdmin == false) {
                    return@flatMap Either.Left(ErrorResponse(403, "User is not admin"))
                }

                membershipRepository.updateMembership(membership)
                    .mapLeft { error -> ErrorResponse(500, "Unknown error updating membership") }
            }
    }

    override fun isUserAdmin(groupId: Int, userId: String): Either<ErrorResponse, Boolean> {
        return membershipRepository.getMembership(userId, groupId)
            .mapRight { membership ->
                if (membership?.isAdmin == true) return@mapRight true;

                false
            }.mapLeft { error ->
                ErrorResponse(500, "Unknown error")
            }
    }

    override fun isUserAcceptedMember(groupId: Int, userId: String): Either<ErrorResponse, Boolean> {
        return membershipRepository.getMembership(userId, groupId)
            .mapRight { membership ->
                if (membership?.status == MembershipStatus.Accepted) return@mapRight true;

                false
            }.mapLeft { error ->
                ErrorResponse(500, "Unknown error")
            }
    }
}