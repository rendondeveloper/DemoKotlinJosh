package com.asdeporte.asdeportev2.data.responses


data class ErrorResponse (
    val code: Int,
    val data: ErrorData,
)

data class ErrorData (
    val error: Boolean,
    val message: String,
)