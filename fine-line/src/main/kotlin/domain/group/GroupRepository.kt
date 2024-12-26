package domain.group

import common.either.Either
import common.error.RepositoryError
import domain.group.models.Group
import domain.group.models.GroupCreateModel

interface GroupRepository {
    fun getAllGroups(): List<Group>
    fun createGroup(group: GroupCreateModel): Either<RepositoryError, Int>
}