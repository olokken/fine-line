package domain.group

import common.either.Either
import common.either.flatMap
import common.either.mapLeft
import common.either.mapRight
import common.error.DatabaseExceptionType
import common.error.ErrorResponse
import domain.group.models.Group
import domain.group.models.GroupCreateModel
import domain.membership.MembershipService
import domain.membership.models.Membership
import domain.membership.models.MembershipStatus
import org.jetbrains.exposed.sql.transactions.transaction

class GroupServiceImpl(private val groupRepository: GroupRepository, private val membershipService: MembershipService) :
    GroupService {
    override fun getAllGroups(): List<Group> {
        return groupRepository.getAllGroups()
    }

    override fun createGroup(group: GroupCreateModel): Either<ErrorResponse, Group> =
        transaction {
            groupRepository.createGroup(group)
                .mapLeft { error ->
                    when (error.exceptionType) {
                        DatabaseExceptionType.UniqueConstraintViolation -> ErrorResponse(
                            409,
                            "Group name already exists"
                        )

                        else -> ErrorResponse(500, "Unknown error creating group")
                    }
                }
        }
            .flatMap { groupId ->
                membershipService.addMembership(Membership(group.creatorId, groupId, true, MembershipStatus.Accepted))
                    .mapRight {
                        Group(
                            groupId = groupId,
                            name = group.name,
                            admins = emptyList(),
                            members = emptyList(),
                            pendingMembers = emptyList(),
                            fineTypes = emptyList()
                        )
                    }
            }

    override fun getGroup(groupId: Int, requestorId: String): Either<ErrorResponse, Group> {
        return membershipService.isUserAcceptedMember(groupId, requestorId)
            .mapLeft { ErrorResponse(500, "Unknown error checking if user is member") }
            .flatMap { isAcceptedUser ->
                if (!isAcceptedUser) return@flatMap Either.Left(ErrorResponse(403, "User is not a member"))

                groupRepository.getGroup(groupId)
                    .mapLeft { ErrorResponse(500, "Unknown error") }
                    .flatMap { group ->
                        if (group == null) {
                            return@flatMap Either.Left(ErrorResponse(404, "Group not found"))
                        }

                        Either.Right(group)
                    }


            }
    }
}
