package domain.group

import domain.group.models.Group
import domain.group.models.GroupCreateModel

interface GroupRepository {
    fun getAllGroups(): List<Group>
    fun createGroup(group: GroupCreateModel): Group
}