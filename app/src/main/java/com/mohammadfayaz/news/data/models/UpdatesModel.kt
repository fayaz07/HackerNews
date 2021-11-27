package com.mohammadfayaz.news.data.models

import com.google.gson.annotations.SerializedName

data class UpdatesModel(
  @SerializedName("profiles") val profiles: List<String> = emptyList(),
  @SerializedName("items") val items: List<Int> = emptyList()
)
