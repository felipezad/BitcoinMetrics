package com.crypto.currency.di.features

import com.crypto.currency.model.ActionResult

interface UseCaseConsumerProducer<in T, out K : Any> : UseCase {
    suspend fun execute(param: T): ActionResult<K>
}

interface UseCaseProducer<out K : Any> : UseCase {
    suspend fun execute(): ActionResult<K>
}

interface UseCase {
    val className: String
}