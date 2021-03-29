package com.crypto.currency.di

import com.squareup.moshi.*
import com.squareup.moshi.internal.Util
import java.lang.reflect.Type

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WhenNullReturnEmpty

object WhenNullReturnEmptyFactory : JsonAdapter.Factory {

    private val annotationClass = WhenNullReturnEmpty::class.java

    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        if (annotations containsAssignable annotationClass) return Adapter
        return null
    }

    private object Adapter : JsonAdapter<String>() {
        override fun fromJson(reader: JsonReader): String? {
            with(reader) {
                return when (peek()) {
                    JsonReader.Token.NULL -> {
                        nextNull<Any>()
                        ""
                    }
                    else -> {
                        nextString()
                    }
                }
            }
        }

        override fun toJson(writer: JsonWriter, value: String?) {
            writer.value(value.toString())
        }
    }
}

private infix fun Set<Annotation>.containsAssignable(clazz: Class<out Annotation>): Boolean {
    return Util.isAnnotationPresent(this, clazz)
}