package com.mohammadfayaz.hn.network

import com.google.gson.Gson

class GsonInstance {
  companion object {
    fun instance(): Gson {
      return Gson()
    }
  }
}
