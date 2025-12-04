package com.example.hpapiapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HpApiClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://hp-api.onrender.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: HpApiService = retrofit.create(HpApiService::class.java)
}
