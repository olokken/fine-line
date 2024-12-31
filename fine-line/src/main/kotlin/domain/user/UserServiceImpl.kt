package domain.user

import common.either.Either
import common.either.flatMap
import common.either.mapLeft
import common.error.ErrorResponse
import domain.user.models.CreateUserModel
import domain.user.models.User
import domain.user.models.UserService

class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun createOrGetUser(user: CreateUserModel): Either<ErrorResponse, User> {
        return userRepository.getUser(user.userId)
            .flatMap { existingUser ->
                if(existingUser != null) return@flatMap Either.Right(existingUser)

               userRepository.createUser(user)
                   .mapLeft { error -> ErrorResponse(500, "Failed to create user") }
            }
            .mapLeft { error -> ErrorResponse(500, "Failed to get user") }
    }
}