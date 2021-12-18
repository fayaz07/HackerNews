package com.mohammadfayaz.hn.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mohammadfayaz.hn.data.models.ApiResult
import com.mohammadfayaz.hn.data.models.StoryModel
import com.mohammadfayaz.hn.domain.DataConfig.MAX_ITEMS_LIMIT
import com.mohammadfayaz.hn.domain.DataConfig.START_PAGE_INDEX
import kotlinx.coroutines.*
import timber.log.Timber

class StoryPagingSource(
  private val getItem: suspend (Int) -> ApiResult<StoryModel>,
  private val ids: List<Int>
) :
  PagingSource<Int, StoryModel>() {
  override fun getRefreshKey(state: PagingState<Int, StoryModel>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoryModel> {
    return try {
      withContext(Dispatchers.Default) {
        val position = params.key ?: START_PAGE_INDEX
        Timber.d("Curr position: $position")
        val list = mutableListOf<StoryModel>()

        var minOffset = (position * MAX_ITEMS_LIMIT) - 1

        if (minOffset > ids.size) {
          // no data to fetch
        } else {
          var maxOffset = (position * MAX_ITEMS_LIMIT) - 1 + MAX_ITEMS_LIMIT

          if (maxOffset >= ids.size) {
            maxOffset = ids.size - 1
          }

          if(minOffset >= maxOffset){
            minOffset = maxOffset - MAX_ITEMS_LIMIT
          }

          val multipleIds = ids.subList(
            minOffset,
            maxOffset
          )

          Timber.d(
            "Sublist from : ${((position - 1) * MAX_ITEMS_LIMIT)} to " +
              "${(position - 1) * MAX_ITEMS_LIMIT + MAX_ITEMS_LIMIT}"
          )

          Timber.d("List size: " + ids.size)
          Timber.d("fetching : $multipleIds")

          val runningTasks = multipleIds.map { id ->
            async { // this will allow us to run multiple tasks in parallel
              val apiResponse = getItem(id)
              id to apiResponse // associate id and response for later
            }
          }
//          delay(3000)
          val responses = runningTasks.awaitAll()
          responses.forEach { (_, response) ->
            if (response.success) {
              list.add(response.result!!)
            }
          }
//        Timber.d("Res: $list")
        }
        LoadResult.Page(
          data = list,
          prevKey = if (position == START_PAGE_INDEX) null else position - 1,
          nextKey = if (list.isEmpty()) null else position + 1
        )
      }
    } catch (e: Exception) {
//      LoadResult.Error(e)
      throw e
    }
  }
}
