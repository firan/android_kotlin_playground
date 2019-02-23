package com.example.myapplication.data.webservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    companion object {
        val client: Retrofit
            get() {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                return Retrofit.Builder()
                    .baseUrl("http://192.168.0.18:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
    }
}