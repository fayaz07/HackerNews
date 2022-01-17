package com.mohammadfayaz.hn.data.sources.network.api

import com.mohammadfayaz.hn.data.sources.network.EndPoints
import com.mohammadfayaz.hn.data.sources.network.response.IdsResponse
import com.mohammadfayaz.hn.domain.models.CommentModel
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.models.UpdatesModel
import com.mohammadfayaz.hn.domain.models.UserModel
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
  suspend fun getCommentById(@Path("id") id: Int): Response<CommentModel>

  @GET(EndPoints.USER_BY_ID)
  suspend fun getUserById(@Path("id") id: String): Response<UserModel>
}
