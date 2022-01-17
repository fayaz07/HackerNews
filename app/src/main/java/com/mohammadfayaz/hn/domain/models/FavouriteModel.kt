package com.mohammadfayaz.hn.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohammadfayaz.hn.data.sources.local.HackerNewsDB.Companion.favouritesTable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = favouritesTable)
data class FavouriteModel(
  @PrimaryKey val id: Int,
  val type: StoryType,
  val savedOn: Long = System.currentTimeMillis()
) : Parcelable
