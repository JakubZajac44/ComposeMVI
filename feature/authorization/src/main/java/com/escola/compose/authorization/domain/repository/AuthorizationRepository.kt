package com.escola.compose.authorization.domain.repository

import com.escola.compose.authorization.data.remote.model.UserDto
import com.escola.compose.resource.ApiResult

interface AuthorizationRepository {

    suspend fun loginUser(login: String, password: String): ApiResult<UserDto>
}