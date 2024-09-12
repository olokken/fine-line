package repository.group

import domain.group.models.Group
import repository.configurations.GroupDAO

fun GroupDAO.toDomainModel(): Group {
    return Group(
        id = this.id.value,
        name = this.name,
    )
}
