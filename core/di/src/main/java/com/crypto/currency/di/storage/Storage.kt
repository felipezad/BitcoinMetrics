package com.crypto.currency.di.storage

interface Storage<T> {

    fun putData(param: T): T

    fun getData(): T
}