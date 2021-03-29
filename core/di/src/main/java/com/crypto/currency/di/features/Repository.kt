package com.crypto.currency.di.features

import com.crypto.currency.model.ActionResult

interface Repository<T> {

    suspend fun insertDataIntoRoom(data: T): ActionResult<Boolean>

    suspend fun getElementsFromApi(page: Int): ActionResult<List<T>>

    suspend fun getElementsFromDatabase(): ActionResult<List<T>>
}
