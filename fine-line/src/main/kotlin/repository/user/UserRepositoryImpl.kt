package repository.user

import common.either.Either
import common.error.RepositoryError
import common.error.toRepositoryError
import domain.user.UserRepository
import domain.user.models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.UserTable

class UserRepositoryImpl : UserRepository {
    override fun createUser(user: User): Either<RepositoryError, User> {
        return try {
            transaction {
                UserTable.insert { row ->
                    row[UserTable.userId] = user.userId
                    row[UserTable.name] = user.name
                }
                Either.Right(user)
            }
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }

    override fun getUser(userId: String): Either<RepositoryError, User?> {
        return try {
            transaction {
                val user = UserTable.select { UserTable.userId eq userId }.singleOrNull()
                Either.Right(if (user == null) null else mapToUser(user))
            }
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }
}