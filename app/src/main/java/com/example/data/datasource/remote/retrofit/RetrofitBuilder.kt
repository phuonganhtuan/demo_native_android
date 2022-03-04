package com.example.data.datasource.remote.retrofit

import android.util.Log
import com.example.BuildConfig
import com.example.data.datasource.remote.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    private const val BASE_URL = "https://www.boredapi.com/api/"

    private fun getRetrofit() = run {

        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor { message -> Log.d("Viettel", message) }
            logging.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            client.addInterceptor(logging)
        }
        client.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("key", "value")
                .addHeader("HEADER", "HEADER Value")
                .build()
            chain.proceed(request)
        }
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
