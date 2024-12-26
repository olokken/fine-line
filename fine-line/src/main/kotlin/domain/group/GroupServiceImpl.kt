package domain.group

import common.either.Either
import common.either.flatMap
import common.either.mapLeft
import common.either.mapRight
import common.error.ErrorResponse
import domain.group.models.Group
import domain.group.models.GroupCreateModel
import domain.membership.MembershipService
import org.jetbrains.exposed.sql.transactions.transaction

class GroupServiceImpl(private val groupRepository: GroupRepository, private val membershipService: MembershipService) : GroupService {
    override fun getAllGroups(): List<Group> {
        return groupRepository.getAllGroups()
    }

    override fun createGroup(group: GroupCreateModel): Either<ErrorResponse, Group> =
    transaction {
        groupRepository.createGroup(group)
            .mapLeft { ErrorResponse(500, "Error creating group") }
            .flatMap { groupId ->
                membershipService.addMember(group.creatorId, groupId, true)
                    .mapRight {
                        Group(
                            groupId = groupId,
                            name = group.name,
                            admins = emptyList()
                        )
                    }
            }
    }
}