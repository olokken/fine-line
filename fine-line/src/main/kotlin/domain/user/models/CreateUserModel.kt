package domain.user.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserModel(val userId: String, val name: String)
