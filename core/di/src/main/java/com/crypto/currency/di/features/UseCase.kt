package com.crypto.currency.di.features

import com.crypto.currency.model.ActionResult

interface UseCase<in T, out K : Any> {
    suspend fun execute(param: T): ActionResult<K>
}