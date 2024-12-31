package domain.user

import common.either.Either
import common.error.RepositoryError
import domain.user.models.CreateUserModel
import domain.user.models.User

interface UserRepository {
    fun createUser(user: CreateUserModel): Either<RepositoryError, User>
    fun getUser(userId: String): Either<RepositoryError, User?>
}