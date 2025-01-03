package repository.membership

import common.either.Either
import common.error.RepositoryError
import common.error.toRepositoryError
import domain.membership.MembershipRepository
import domain.membership.models.Membership
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.UserGroupTable

class MembershipRepositoryImpl : MembershipRepository {
    override fun addMembership(membership: Membership): Either<RepositoryError, Boolean> {
        return try {
            transaction {
                UserGroupTable.insert { row ->
                    row[UserGroupTable.userId] = membership.userId
                    row[UserGroupTable.groupId] = membership.groupId
                    row[UserGroupTable.isAdmin] = membership.isAdmin
                    row[UserGroupTable.status] = membership.status
                }
            }
            Either.Right(true)
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }

    override fun updateMembership(
        membership: Membership
    ): Either<RepositoryError, Boolean> {
        return try {
            transaction {
                UserGroupTable.update({
                    UserGroupTable.userId eq membership.userId and (UserGroupTable.groupId eq membership.groupId)
                }) { row ->
                    row[UserGroupTable.isAdmin] = membership.isAdmin
                    row[UserGroupTable.status] = membership.status
                }

                Either.Right(true)
            }
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }

    override fun getMembership(
        userId: String,
        groupId: Int
    ): Either<RepositoryError, Membership?> {
        return try {
            transaction {
                val membership =
                    UserGroupTable.select { (UserGroupTable.userId eq userId) and (UserGroupTable.groupId eq groupId) }
                        .singleOrNull()
                if(membership == null) return@transaction Either.Right(null)
                Either.Right(mapToMembership(membership))
            }
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }
}
