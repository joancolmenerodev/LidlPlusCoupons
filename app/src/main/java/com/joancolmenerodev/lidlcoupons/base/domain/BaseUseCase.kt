package com.joancolmenerodev.lidlcoupons.base.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

open class BaseUseCase {

    inline fun <reified L : Exception, R> toEither(block: () -> R): Either<L, R> {
        return try {
            Either.Right(block())
        } catch (e: Exception) {
            when (e) {
                is L -> Either.Left(e)
                else -> throw e
            }
        }
    }

    inline fun <reified L : Exception, R> Flow<R>.toFlowEither(): Flow<Either<L, R>> = flow {
        catch { e ->
            when (e) {
                is L -> emit(Either.Left(e))
                else -> throw e
            }
        }
            .collect { item ->
                emit(Either.Right(item))
            }
    }
}