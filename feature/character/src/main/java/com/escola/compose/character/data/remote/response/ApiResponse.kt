package com.escola.compose.character.data.remote.response

data class ApiResponse<T>(
    val info: ApiResponseInfo,
    val results: List<T>
)

data class ApiResponseInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)