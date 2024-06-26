package com.escola.compose.authorization.data.mapper

import com.escola.compose.authorization.data.remote.model.UserDto
import com.escola.compose.authorization.domain.model.UserModel

fun UserDto.toUser(): UserModel {
    return UserModel(
        id = id,
        image = image,
        name = name
    )
}