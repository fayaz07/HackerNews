package com.mohammadfayaz.hn.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mohammadfayaz.hn.utils.AppConstants.storiesTable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = storiesTable)
data class StoryModel(
  @PrimaryKey @SerializedName("id") val id: Int,
  @SerializedName("type") val type: String,

  @SerializedName("title") val title: String? = "",
  @SerializedName("url") val url: String? = "",
  @SerializedName("by") val by: String? = "hn",
  @SerializedName("dead") val dead: Boolean? = false,
  @SerializedName("deleted") val deleted: Boolean? = false,
  @SerializedName("descendants") val descendants: Int? = 0,
  @SerializedName("parent") val parent: Int? = -1,
  @SerializedName("score") val score: Int? = 0,
  @SerializedName("text") val text: String? = "",
  @SerializedName("time") val time: Long? = 0,

  @SerializedName("kids") var kids: List<Int>? = emptyList(),
  @SerializedName("parts") var parts: List<Int>? = emptyList(),
  var storyType: StoryType = StoryType.SHOW
) : Parcelable {
  fun setDefaults() {
    if (kids == null) {
      kids = emptyList()
    }

    if (parts == null) {
      parts = emptyList()
    }
  }
}
