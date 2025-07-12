package com.example.baith2.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    // Tạo interceptor để ghi log HTTP
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Đặt mức log là BODY
    }

    // Cấu hình OkHttpClient với logger và timeout
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS) // Thời gian chờ kết nối
        .readTimeout(30, TimeUnit.SECONDS)    // Thời gian chờ đọc dữ liệu
        .writeTimeout(30, TimeUnit.SECONDS)   // Thời gian chờ ghi dữ liệu
        .build()

    // Khởi tạo Retrofit API Service
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://mock.apidog.com/") // URL cơ sở cho API
            .client(okHttpClient) // Sử dụng OkHttpClient đã cấu hình
            .addConverterFactory(GsonConverterFactory.create()) // Chuyển đổi JSON sang đối tượng Kotlin
            .build()
            .create(ApiService::class.java) // Tạo instance của ApiService
    }
}