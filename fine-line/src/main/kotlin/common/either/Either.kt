package common.either

sealed interface Either<out A, out B> {
    data class Left<out A>(
        val value: A
    ) : Either<A, Nothing>

    data class Right<out B>(
        val value: B
    ) : Either<Nothing, B>
}

fun <A, B, C> Either<A, B>.mapLeft(f: (A) -> C): Either<C, B> =
    when (this) {
        is Either.Left -> Either.Left(f(this.value))
        is Either.Right -> Either.Right(this.value)
    }

fun <A, B, C> Either<A, B>.mapRight(f: (B) -> C): Either<A, C> =
    when (this) {
        is Either.Left -> Either.Left((this.value))
        is Either.Right -> Either.Right(f(this.value))
    }

fun <A, B, C> Either<A, B>.flatMap(f: (B) -> Either<A, C>): Either<A, C> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> f(this.value)
    }

fun <A, B, R> Either<A, B>.fold(
    left: (A) -> R,
    right: (B) -> R
): R = when (this) {
    is Either.Left -> left(value)
    is Either.Right -> right(value)
}

suspend fun <A, B, R> Either<A, B>.foldSuspend(
    left: suspend (A) -> R,
    right: suspend (B) -> R
): R = when (this) {
    is Either.Left -> left(value)
    is Either.Right -> right(value)
}

fun <A> A.left(): Either<A, Nothing> = Either.Left(this)

fun <A> A.right(): Either<Nothing, A> = Either.Right(this)

fun <T> Result<T>.toEither(): Either<Throwable, T> =
    this
        .map { it.right() }
        .getOrElse { throwable ->
            throwable.left()
        }

fun <A, B> Either<A, B>.isLeft(): Boolean = this is Either.Left
fun <A, B> Either<A, B>.isRight(): Boolean = this is Either.Right
