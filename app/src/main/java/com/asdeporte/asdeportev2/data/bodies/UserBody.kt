package com.asdeporte.asdeportev2.data.bodies

import com.google.gson.annotations.SerializedName

open class LoginRequestDTO(
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null
)
