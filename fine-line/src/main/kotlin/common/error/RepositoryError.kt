package common.error

data class RepositoryError(val exceptionType: DatabaseExceptionType, val message: String?)

enum class DatabaseExceptionType {
    UniqueConstraintViolation,
    ForeignKeyConstraintViolation,
    NullConstraintViolation,
    Unknown
}

fun Throwable.toRepositoryError() : RepositoryError {
    return RepositoryError(DatabaseExceptionType.Unknown, this.message);
}




