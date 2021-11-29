package com.mohammadfayaz.news.data.models

data class ApiResult<T>(val data: T?, val success: Boolean, val message: String = "")
