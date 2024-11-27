package com.example.dndspells.api

import com.example.dndspells.model.SpellDetails
import com.example.dndspells.model.SpellResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DndApiService {
    @GET("spells")
    suspend fun getSpells(): SpellResponse

    @GET("spells/{index}")
    suspend fun getSpellDetails(@Path("index") index: String): SpellDetails
}
