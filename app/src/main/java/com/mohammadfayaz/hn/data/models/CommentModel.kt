package com.mohammadfayaz.hn.data.models

import com.google.gson.annotations.SerializedName

data class CommentModel(
  @SerializedName("id") val id: Int = 0,
  @SerializedName("parent") val parent: Int = 0,
  @SerializedName("by") val by: String = "",
  @SerializedName("text") val text: String = "",
  @SerializedName("time") val time: Long = 0,
  @SerializedName("type") val type: String = "",
  @SerializedName("kids") val kids: List<Int>?
)
