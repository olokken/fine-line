package domain.user.models

import common.either.Either
import common.error.ErrorResponse

interface UserService {
    fun createOrGetUser(user: CreateUserModel): Either<ErrorResponse, User>
}