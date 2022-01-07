package com.mohammadfayaz.hn.data.sources.network.source

import com.mohammadfayaz.hn.data.sources.network.ResultWrapper
import com.mohammadfayaz.hn.data.sources.network.api.HackerNewsAPI
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryModel

class StoriesNetworkSource(private val api: HackerNewsAPI) : BaseNetworkSource() {
  suspend fun getStoryById(id: Int): ApiResult<StoryModel> {
    return when (val fromNetwork = ResultWrapper.safeApiCall { api.getStoryById(id) }) {
      is ResultWrapper.GenericError -> ApiResult.ERROR(fromNetwork.error)
      ResultWrapper.NetworkError -> ApiResult.NetworkError
      is ResultWrapper.Success -> {
        if (fromNetwork.value.body() != null) {
          ApiResult.OK(res = fromNetwork.value.body()!!)
        } else {
          ApiResult.ERROR("Unable to fetch data")
        }
      }
    }
  }
}
