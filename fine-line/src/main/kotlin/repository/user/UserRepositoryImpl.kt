package repository.user

import common.either.Either
import common.error.RepositoryError
import common.error.toRepositoryError
import domain.membership.models.MembershipStatus
import domain.user.UserRepository
import domain.user.models.CreateUserModel
import domain.user.models.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.GroupTable
import repository.configurations.UserGroupTable
import repository.configurations.UserTable
import repository.group.mapToGroup

class UserRepositoryImpl : UserRepository {
    override fun createUser(user: CreateUserModel): Either<RepositoryError, User> {
        return try {
            transaction {
                UserTable.insert { row ->
                    row[UserTable.userId] = user.userId
                    row[UserTable.name] = user.name
                }
                Either.Right(User(user.userId, user.name, emptyList()))
            }
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }

    override fun getUser(userId: String): Either<RepositoryError, User?> {
        return try {
            transaction {
                val user = UserTable.select { UserTable.userId eq userId }.singleOrNull()
                if(user == null) return@transaction Either.Right(null)

                val groups = (UserGroupTable innerJoin GroupTable)
                    .select { (UserGroupTable.userId eq userId) and (UserGroupTable.status eq MembershipStatus.Accepted) }
                    .map { mapToGroup(it, emptyList()) }

                val mappedUser = mapToUser(user, groups)

                return@transaction Either.Right(mappedUser)
            }
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }
}