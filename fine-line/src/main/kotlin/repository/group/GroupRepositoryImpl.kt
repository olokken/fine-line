package repository.group

import common.either.Either
import common.error.RepositoryError
import common.error.toRepositoryError
import domain.group.GroupRepository
import domain.group.models.Group
import domain.group.models.GroupCreateModel
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.FineTypeTable
import repository.configurations.GroupTable
import repository.configurations.UserGroupTable
import repository.configurations.UserTable
import repository.fineType.mapToFineType

class GroupRepositoryImpl : GroupRepository {
    override fun getAllGroups(): List<Group> {
        return transaction {
            GroupTable.selectAll().map { row -> mapToGroup(row, emptyList(), emptyList()) };
        }
    }

    override fun createGroup(group: GroupCreateModel): Either<RepositoryError, Int> {
        return try {
            Either.Right(transaction {
                GroupTable.insert { row ->
                    row[name] = group.name
                } get GroupTable.groupId
            })
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }

    override fun getGroup(groupId: Int): Either<RepositoryError, Group?> {
        return try {
            transaction {
                val group = GroupTable.select { GroupTable.groupId eq groupId }.singleOrNull()
                if (group == null) return@transaction Either.Right(null)

                val users = (UserGroupTable innerJoin UserTable)
                    .select { (UserGroupTable.groupId eq groupId) }
                    .map { mapToUserInGroup(it) }

                val fineTypes = FineTypeTable.select { FineTypeTable.groupId eq groupId }.map { mapToFineType(it) }

                Either.Right(mapToGroup(group, users, fineTypes))
            }
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }
}