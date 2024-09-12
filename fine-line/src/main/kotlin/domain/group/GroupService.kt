package domain.group

import domain.group.models.Group
import domain.group.models.GroupCreateModel
import domain.group.models.GroupUpdateModel

interface GroupService {
    fun getAllGroups(): List<Group>
    fun getGroupById(id: String): Group?
    fun createGroup(group: GroupCreateModel): Group
    fun updateGroup(group: GroupUpdateModel): Group
    fun deleteGroup(id: Int): Boolean
}