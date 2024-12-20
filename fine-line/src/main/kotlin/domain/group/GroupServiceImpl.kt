package domain.group

import domain.group.models.Group
import domain.group.models.GroupCreateModel
import domain.group.models.GroupUpdateModel

class GroupServiceImpl(private val groupRepository: GroupRepository) : GroupService {
    override fun getAllGroups(): List<Group> {
        return groupRepository.getAllGroups()
    }

    override fun getGroupById(id: String): Group? {
        TODO("Not yet implemented")
    }

    override fun createGroup(group: GroupCreateModel): Group {
        return groupRepository.createGroup(group)
    }

    override fun updateGroup(group: GroupUpdateModel): Group {
        TODO("Not yet implemented")
    }

    override fun deleteGroup(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}