package com.escola.compose.authorization.data.repository

import com.escola.compose.authorization.data.remote.model.UserDto
import com.escola.compose.authorization.data.remote.AuthorizationRemoteDataSource
import com.escola.compose.authorization.domain.repository.AuthorizationRepository
import com.escola.compose.resource.ApiResult
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val authorizationRemoteDataSource: AuthorizationRemoteDataSource
) : AuthorizationRepository {

    override suspend fun loginUser(login: String, password: String): ApiResult<UserDto> {
        return authorizationRemoteDataSource.loginUser(login = login, password = password)
    }
}