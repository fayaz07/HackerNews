package com.mohammadfayaz.news.network.api

import com.mohammadfayaz.news.data.models.*
import com.mohammadfayaz.news.network.EndPoints
import com.mohammadfayaz.news.network.models.response.IdsResponse
import retrofit2.Response
import retrofit2.http.GET

interface HackerNewsAPI {

  @GET(EndPoints.TOP_STORIES)
  suspend fun getTopStories(): Response<IdsResponse>

  @GET(EndPoints.SHOW_STORIES)
  suspend fun getShowStories(): Response<IdsResponse>

  @GET(EndPoints.ASK_STORIES)
  suspend fun getAskStories(): Response<IdsResponse>

  @GET(EndPoints.JOB_STORIES)
  suspend fun getJobStories(id: Int): Response<IdsResponse>

  @GET(EndPoints.UPDATES)
  suspend fun getUpdates(id: String): Response<UpdatesModel>

  @GET(EndPoints.ITEM_BY_ID)
  suspend fun getShowStoryById(id: Int): Response<ShowStoryModel>

  @GET(EndPoints.ITEM_BY_ID)
  suspend fun getAskStoryById(id: Int): Response<AskStoryModel>

  @GET(EndPoints.ITEM_BY_ID)
  suspend fun getTopStoryById(id: Int): Response<TopStoryModel>

  @GET(EndPoints.ITEM_BY_ID)
  suspend fun getJobById(id: Int): Response<JobStoryModel>

  @GET(EndPoints.USER_BY_ID)
  suspend fun getUserById(id: String): Response<UserModel>
  
}
