package domain.user.models

import kotlinx.serialization.Serializable

@Serializable
data class UserCreateModel(val userId: String, val name: String)
