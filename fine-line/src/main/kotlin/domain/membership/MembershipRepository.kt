package domain.membership

import common.either.Either
import common.error.RepositoryError
import domain.membership.models.Membership

interface MembershipRepository {
    fun addMembership(membership: Membership) : Either<RepositoryError, Boolean>;
    fun updateMembership(membership: Membership): Either<RepositoryError, Membership>;
    fun getMembership(userId: String, groupId: Int): Either<RepositoryError, Membership?>;
    fun deleteMembership(userId: String, groupId: Int): Either<RepositoryError, Boolean>
}