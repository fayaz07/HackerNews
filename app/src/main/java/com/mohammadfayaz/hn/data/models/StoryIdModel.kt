package com.mohammadfayaz.hn.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohammadfayaz.hn.data.db.HackerNewsDB.Companion.idsTable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = idsTable)
data class StoryIdModel(
  @PrimaryKey val id: Int,
  val type: StoryType,
  val birth: Long = System.currentTimeMillis()
) : Parcelable
