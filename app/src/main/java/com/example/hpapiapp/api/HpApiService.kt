package com.example.hpapiapp.api

import com.example.hpapiapp.model.HpCharacter
import retrofit2.http.GET
import retrofit2.http.Path

interface HpApiService {

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): List<HpCharacter>

    @GET("api/characters/staff")
    suspend fun getStaff(): List<HpCharacter>

    @GET("api/characters/house/{house}")
    suspend fun getCharactersByHouse(@Path("house") house: String): List<HpCharacter>
}
