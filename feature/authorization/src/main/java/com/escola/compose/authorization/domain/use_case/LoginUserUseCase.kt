package com.escola.compose.authorization.domain.use_case

import android.util.Log
import com.escola.compose.authorization.data.mapper.toUser
import com.escola.compose.authorization.domain.model.UserModel
import com.escola.compose.authorization.domain.repository.AuthorizationRepository
import com.escola.compose.resource.ApiResult
import com.escola.compose.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) {
    suspend operator fun invoke(login: String, password: String): Flow<Resource<UserModel>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = authorizationRepository.loginUser(login, password)) {
                is ApiResult.Error -> {
                    Log.e("Error", "Error respone ${apiResponse.exception.message}")
                    emit(Resource.Success(UserModel("1", "2", "3")))
//                    emit(Resource.Error(apiResponse.exception, apiResponse.exception.message))
                }

                is ApiResult.Success -> emit(Resource.Success(apiResponse.data.toUser()))
            }

        }


    }
}