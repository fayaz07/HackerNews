package com.mohammadfayaz.news.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mohammadfayaz.news.utils.AppConstants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = AppConstants.showStoryTable)
data class ShowStoryModel(
  @PrimaryKey @SerializedName("id") val id: Int = 0,
  @SerializedName("score") val score: Int = 0,
  @SerializedName("by") val by: String = "",
  @SerializedName("text") val text: String = "",
  @SerializedName("time") val time: Long = 0,
  @SerializedName("title") val title: String = "",
  @SerializedName("type") val type: String = "",
  @SerializedName("descendants") val descendants: Int = 0,
  @SerializedName("url") val url: String = "",
  @SerializedName("kids") val kids: List<Int> = emptyList()
) : Parcelable
