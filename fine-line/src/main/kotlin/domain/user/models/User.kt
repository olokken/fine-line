package domain.user.models

import domain.group.models.Group

data class User(val userId: String, val name: String, val groups: List<Group>)
