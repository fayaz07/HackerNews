package com.mohammadfayaz.news.repository

import com.mohammadfayaz.news.network.api.HackerNewsAPI
import com.mohammadfayaz.news.network.models.response.IdsResponse
import retrofit2.Response
import javax.inject.Inject

class HackerNewsRepo @Inject constructor(private val api: HackerNewsAPI) {

  suspend fun pullTopStories(): Response<IdsResponse> {
    return api.getTopStories()
  }

}
