package com.asdeporte.asdeportev2.data.responses.user

data class UserResponse(
    val code: Int?,
    val data: UserData?,
)
data class UserData(
    val first_name: String?,
    val userid: String?,
)