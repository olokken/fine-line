package repository.membership

import common.either.Either
import common.either.left
import common.either.right
import common.error.ErrorResponse
import common.error.RepositoryError
import common.error.toRepositoryError
import domain.membership.MembershipRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.UserGroupTable

class MembershipRepositoryImpl : MembershipRepository {
    override fun addMember(userId: String, groupId: Int, isAdmin: Boolean): Either<RepositoryError, Boolean> {
        return try {
            transaction {
                UserGroupTable.insert { row ->
                    row[UserGroupTable.userId] = userId
                    row[UserGroupTable.groupId] = groupId
                    row[UserGroupTable.isAdmin] = isAdmin
                }
            }
            Either.Right(true)
        }
        catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }
}