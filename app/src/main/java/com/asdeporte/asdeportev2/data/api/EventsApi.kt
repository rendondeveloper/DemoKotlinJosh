package com.asdeporte.asdeportev2.data.api

import com.asdeporte.asdeportev2.data.responses.user.UserResponse
import com.asdeporte.asdeportev2.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface EventsApi {

    @GET("event/{eventId}")
    @Headers("x-asd-apikey: ${Constants.API_KEY}")
    suspend fun getEvent(@Header("x-asd-usrtoken")userToken: String?,
                        @Header("x-asd-lang")lang: String?,
                        @Path("eventId")eventId: String): Response<UserResponse>

}