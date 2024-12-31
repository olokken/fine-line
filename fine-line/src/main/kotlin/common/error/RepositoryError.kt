package common.error

import org.jetbrains.exposed.exceptions.ExposedSQLException

data class RepositoryError(val exceptionType: DatabaseExceptionType, val message: String?)

enum class DatabaseExceptionType {
    UniqueConstraintViolation,
    ForeignKeyConstraintViolation,
    NullConstraintViolation,
    Unknown
}

fun Throwable.toRepositoryError(): RepositoryError = when (this) {
    is ExposedSQLException -> when (sqlState) {
        "23505" -> RepositoryError(DatabaseExceptionType.UniqueConstraintViolation, message)
        else -> RepositoryError(DatabaseExceptionType.Unknown, message)
    }
    else -> RepositoryError(DatabaseExceptionType.Unknown, message)
}




