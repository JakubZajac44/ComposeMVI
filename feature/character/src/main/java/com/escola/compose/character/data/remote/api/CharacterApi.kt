package com.escola.compose.character.data.remote.api

import com.escola.compose.character.data.remote.model.CharacterDto
import com.escola.compose.character.data.remote.model.EpisodeDto
import com.escola.compose.character.data.remote.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character/")
    suspend fun getCharacterList(
        @Query("page") page: Int,
    ): Response<ApiResponse<CharacterDto>>

    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id: String,
    ): Response<CharacterDto>

    @GET("episode/{id}")
    suspend fun getCharacterEpisode(
        @Path("id") id: String,
    ): Response<EpisodeDto>
}