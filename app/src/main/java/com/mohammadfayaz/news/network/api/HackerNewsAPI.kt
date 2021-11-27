package com.mohammadfayaz.news.network.api

import com.mohammadfayaz.news.network.EndPoints
import com.mohammadfayaz.news.network.models.response.TopStoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface HackerNewsAPI {

  @GET(EndPoints.TOP_STORIES)
  suspend fun fetchTopStories(): Response<TopStoriesResponse>

}

