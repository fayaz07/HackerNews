package com.mohammadfayaz.hn.data.sources.network.source

import com.mohammadfayaz.hn.data.sources.network.ResultWrapper
import com.mohammadfayaz.hn.data.sources.network.api.HackerNewsAPI
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryIdModel
import com.mohammadfayaz.hn.domain.models.StoryType

class IdsNetworkSource(private val api: HackerNewsAPI) : BaseNetworkSource() {
  suspend fun getIdsByType(storyType: StoryType): ApiResult<List<Int>> {
    val idsList = mutableSetOf<Int>()

    return when (
      val networkResponse = ResultWrapper.safeApiCall {
        api.getShowStories()
      }
    ) {
      is ResultWrapper.GenericError ->
        ApiResult.ERROR(networkResponse.error + ", no data available in cached storage")
      ResultWrapper.NetworkError ->
        ApiResult.NetworkError
      is ResultWrapper.Success -> {
        if (networkResponse.value.body() != null) {
          val list = mutableListOf<StoryIdModel>()
          val currentTimestamp = System.currentTimeMillis()
          for (i in networkResponse.value.body()!!) {
            idsList.add(i)
            list.add(StoryIdModel(i, storyType, currentTimestamp))
          }
          ApiResult.OK("", idsList.toList())
        } else {
          ApiResult.ERROR("No data found")
        }
      }
    }
  }
}
