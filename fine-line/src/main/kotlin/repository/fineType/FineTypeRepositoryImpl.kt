package repository.fineType

import common.either.Either
import common.error.RepositoryError
import common.error.toRepositoryError
import domain.fineType.FineTypeRepository
import domain.fineType.models.FineTypeCreateModel
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.FineTypeTable

class FineTypeRepositoryImpl : FineTypeRepository {
    override fun createFineType(createModel: FineTypeCreateModel): Either<RepositoryError, Int> {
        return try {
            Either.Right(transaction {
                FineTypeTable.insert { row ->
                    row[name] = createModel.name
                    row[description] = createModel.description
                    row[sum] = createModel.sum
                    row[groupId] = createModel.groupId
                } get FineTypeTable.groupId
            })
        } catch (error: Throwable) {
            Either.Left(error.toRepositoryError())
        }
    }
}