package com.mohammadfayaz.news.data.models

import com.google.gson.annotations.SerializedName

data class JobStoryModel(
  @SerializedName("id") val id: Int = 0,
  @SerializedName("score") val score: Int = 0,
  @SerializedName("by") val by: String = "",
  @SerializedName("text") val text: String = "",
  @SerializedName("time") val time: Long = 0,
  @SerializedName("title") val title: String = "",
  @SerializedName("type") val type: String = "",
  @SerializedName("url") val url: String = ""
)
