package com.mohammadfayaz.hn.network.api

import com.mohammadfayaz.hn.data.models.*
import com.mohammadfayaz.hn.network.EndPoints
import com.mohammadfayaz.hn.network.models.response.IdsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsAPI {

  @GET(EndPoints.TOP_STORIES)
  suspend fun getTopStories(): Response<IdsResponse>

  @GET(EndPoints.SHOW_STORIES)
  suspend fun getShowStories(): Response<IdsResponse>

  @GET(EndPoints.ASK_STORIES)
  suspend fun getAskStories(): Response<IdsResponse>

  @GET(EndPoints.JOB_STORIES)
  suspend fun getJobStories(): Response<IdsResponse>

  @GET(EndPoints.UPDATES)
  suspend fun getUpdates(): Response<UpdatesModel>

  @GET(EndPoints.ITEM_BY_ID)
  suspend fun getStoryById(@Path("id") id: Int): Response<StoryModel>
  
  @GET(EndPoints.ITEM_BY_ID)
  suspend fun getShowStoryById(@Path("id") id: Int): Response<ShowStoryModel>

  @GET(EndPoints.ITEM_BY_ID)
  suspend fun getAskStoryById(@Path("id") id: Int): Response<AskStoryModel>

  @GET(EndPoints.ITEM_BY_ID)
  suspend fun getTopStoryById(@Path("id") id: Int): Response<TopStoryModel>

  @GET(EndPoints.ITEM_BY_ID)
  suspend fun getJobById(@Path("id") id: Int): Response<JobStoryModel>

  @GET(EndPoints.USER_BY_ID)
  suspend fun getUserById(@Path("id") id: String): Response<UserModel>

}