package domain.user

import common.either.Either
import common.error.RepositoryError
import domain.user.models.UserCreateModel
import domain.user.models.User

interface UserRepository {
    fun createUser(user: UserCreateModel): Either<RepositoryError, User>
    fun getUser(userId: String): Either<RepositoryError, User?>
}