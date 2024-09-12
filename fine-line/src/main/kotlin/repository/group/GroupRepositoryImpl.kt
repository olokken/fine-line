package repository.group

import domain.group.GroupRepository
import domain.group.models.Group
import domain.group.models.GroupCreateModel
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.GroupDAO

class GroupRepositoryImpl : GroupRepository {
    override fun getAllGroups(): List<Group> {
        return transaction {
            GroupDAO.all().map(GroupDAO::toDomainModel).toList();
        }
    }

    override fun createGroup(group: GroupCreateModel): Group {
        return transaction {
            val groupDAO = GroupDAO.new {
                this.name = group.name
            };

            groupDAO.toDomainModel();
        }
    }
}