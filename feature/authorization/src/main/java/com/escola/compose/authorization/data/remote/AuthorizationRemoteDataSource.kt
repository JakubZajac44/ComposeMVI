package com.escola.compose.authorization.data.remote

import com.escola.compose.authorization.data.remote.model.UserDto
import com.escola.compose.resource.ApiResult
import com.escola.compose.resource.apiCall
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthorizationRemoteDataSource @Inject constructor(
    private val api: AuthorizationApi
) {
    suspend fun loginUser(login: String, password: String): ApiResult<UserDto> {
        return apiCall(call = { api.loginUser() })
    }
}