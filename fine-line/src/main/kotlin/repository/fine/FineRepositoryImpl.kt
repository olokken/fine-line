package repository.fine

import common.either.Either
import common.error.RepositoryError
import common.error.toRepositoryError
import domain.fine.FineRepository
import domain.fine.models.FineCreateModel
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.FineTable

class FineRepositoryImpl : FineRepository {
    override fun createFine(createModel: FineCreateModel): Either<RepositoryError, Int> {
        return try {
            Either.Right(transaction {
                return@transaction FineTable.insert { row ->
                    row[fineTypeId] = createModel.fineTypeId
                    row[groupId] = createModel.groupId
                    row[issuerId] = createModel.issuerId
                    row[receiverId] = createModel.receiverId
                    row[timeIssued] = createModel.timeIssued
                } get FineTable.fineId
            })
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }
}