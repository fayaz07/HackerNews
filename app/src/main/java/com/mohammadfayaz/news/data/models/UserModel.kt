package com.mohammadfayaz.news.data.models

import com.google.gson.annotations.SerializedName

data class UserModel(
  @SerializedName("id") val id: String = "",
  @SerializedName("karma") val karma: Int = 0,
  @SerializedName("delay") val delay: Long = 0,
  @SerializedName("submitted") val submitted: List<Int> = emptyList(),
  @SerializedName("created") val created: Int = 0,
  @SerializedName("about") val about: String = ""
)
