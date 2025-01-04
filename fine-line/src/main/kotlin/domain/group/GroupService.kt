package domain.group

import common.either.Either
import common.error.ErrorResponse
import domain.group.models.Group
import domain.group.models.GroupCreateModel

interface GroupService {
    fun getAllGroups(): List<Group>
    fun createGroup(group: GroupCreateModel): Either<ErrorResponse, Group>
    fun getGroup(groupId: Int): Either<ErrorResponse, Group>
}