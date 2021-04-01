package com.crypto.currency.di.features

import com.crypto.currency.model.ActionResult

interface Repository<in Param, out Result : Any> {

    suspend fun getElementsFromApi(param: Param): ActionResult<Result>
}
