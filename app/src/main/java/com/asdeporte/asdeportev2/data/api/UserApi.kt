package com.asdeporte.asdeportev2.data.api

import com.asdeporte.asdeportev2.data.bodies.LoginRequestDTO
import com.asdeporte.asdeportev2.data.responses.ErrorResponse
import com.asdeporte.asdeportev2.data.responses.user.UserResponse
import com.asdeporte.asdeportev2.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("user/auth")
    @Headers("x-asd-apikey: ${Constants.API_KEY}")
    suspend fun makeLogin(@Header("x-asd-lang")lang: String?,
                          @Body loginRequestDTO: LoginRequestDTO): Response<UserResponse>

    @POST("user/{email}/forgot")
    @Headers("x-asd-apikey: ${Constants.API_KEY}")
    suspend fun forgotPassword(@Header("x-asd-lang")lang: String?,
                          @Path("email")email: String): Response<ErrorResponse>



    @GET("user/{userId}")
    @Headers("x-asd-apikey: ${Constants.API_KEY}")
    suspend fun getUser(@Header("x-asd-usrtoken")userToken: String?,
                        @Header("x-asd-lang")lang: String?,
                        @Path("userId")userId: String): Response<UserResponse>

}