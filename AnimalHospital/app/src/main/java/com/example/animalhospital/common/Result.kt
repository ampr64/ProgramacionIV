package com.example.animalhospital.common

import java.io.Serializable

open class Result protected constructor(
    val success: Boolean,
    val error: String? = null
) : Serializable {
    companion object {
        fun ok(): Result {
            return Result(true)
        }

        fun fail(error: String): Result {
            return Result(false, error)
        }
    }
}

class ObjectResult<T> private constructor(
    val obj: T?,
    success: Boolean,
    error: String? = null
) : Result(success, error), Serializable {
    companion object {
        fun <T> ok(obj: T): ObjectResult<T> {
            return ObjectResult(obj, true)
        }

        fun <T> fail(error: String): ObjectResult<T> {
            return ObjectResult(null, false, error)
        }
    }
}