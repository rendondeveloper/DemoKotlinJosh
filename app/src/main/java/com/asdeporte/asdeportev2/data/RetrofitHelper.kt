package com.asdeporte.asdeportev2.data

import com.asdeporte.asdeportev2.data.responses.ErrorResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    private const val TIMEOUT = 40L
    var httpClient: OkHttpClient.Builder

    val baseUrl = "https://ms.asdeporte.com/v1/"

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
    }

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl("https://ms.asdeporte.com/" + "v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getMessageException(message: String?): Exception {
        val error = Gson().fromJson<ErrorResponse>(message, ErrorResponse::class.java)
        return Exception(error.data.message)
    }
}