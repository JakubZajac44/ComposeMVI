package com.escola.compose.authorization.data.remote

import com.escola.compose.authorization.data.remote.model.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorizationApi {

    @GET("character/2")
    suspend fun loginUser(
    ): Response<UserDto>
}