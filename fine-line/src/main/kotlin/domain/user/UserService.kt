package domain.user.models

interface UserService {
    fun createOrUpdateUser(userId: String, name: String);
}