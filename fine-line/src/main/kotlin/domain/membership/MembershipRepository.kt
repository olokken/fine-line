package domain.membership

import common.either.Either
import common.error.RepositoryError

interface MembershipRepository {
    fun addMember(userId: String, groupId: Int, isAdmin: Boolean) : Either<RepositoryError, Boolean>;
}