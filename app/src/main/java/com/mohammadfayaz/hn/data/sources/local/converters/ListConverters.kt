package com.mohammadfayaz.hn.data.sources.local.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.mohammadfayaz.hn.data.sources.network.GsonInstance
import java.lang.reflect.Type

class ListConverters {

  private val stringListType: Type = object : TypeToken<List<String?>?>() {}.type
  private val intListType: Type = object : TypeToken<List<Int?>?>() {}.type

  @TypeConverter
  fun listToString(list: List<String>?): String {
    return GsonInstance.instance().toJson(list)
  }

  @TypeConverter
  fun listFromString(list: String?): List<String> {
    if (list == null) return emptyList()
    return GsonInstance.instance().fromJson(list, stringListType) as List<String>
  }

  @TypeConverter
  fun intListToString(list: List<Int>?): String {
    return GsonInstance.instance().toJson(list)
  }

  @TypeConverter
  fun intListFromString(list: String?): List<Int> {
    if (list == null) return emptyList()
    return GsonInstance.instance().fromJson(list, intListType) as List<Int>
  }
}
