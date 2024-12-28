package repository.group

import common.either.Either
import common.error.RepositoryError
import common.error.toRepositoryError
import domain.group.GroupRepository
import domain.group.models.Group
import domain.group.models.GroupCreateModel
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.GroupTable

class GroupRepositoryImpl : GroupRepository {
    override fun getAllGroups(): List<Group> {
        return transaction {
            GroupTable.selectAll().map { row -> mapToGroup(row) };
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
}