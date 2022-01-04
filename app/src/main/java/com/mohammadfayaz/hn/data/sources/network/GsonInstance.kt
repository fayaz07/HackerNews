package com.mohammadfayaz.hn.data.sources.network

import com.google.gson.Gson

class GsonInstance {
  companion object {
    fun instance(): Gson {
      return Gson()
    }
  }
}
