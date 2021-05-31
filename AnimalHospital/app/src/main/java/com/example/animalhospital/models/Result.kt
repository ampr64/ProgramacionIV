package com.example.animalhospital.models

class Result private constructor(val success: Boolean, val error: String? = null) {
    companion object {
        fun ok(): Result {
            return Result(true)
        }

        fun fail(error: String): Result {
            return Result(false, error)
        }
    }
}