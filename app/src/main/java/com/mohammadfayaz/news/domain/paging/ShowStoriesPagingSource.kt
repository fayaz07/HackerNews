package com.mohammadfayaz.news.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mohammadfayaz.news.data.models.ApiResult
import com.mohammadfayaz.news.data.models.ShowStoryModel
import com.mohammadfayaz.news.domain.DataConfig.MAX_ITEMS_LIMIT
import com.mohammadfayaz.news.domain.DataConfig.START_PAGE_INDEX
import kotlinx.coroutines.*
import timber.log.Timber

class ShowStoriesPagingSource(
  private val getItem: suspend (Int) -> ApiResult<ShowStoryModel>,
  private val ids: List<Int>
) :
  PagingSource<Int, ShowStoryModel>() {
  override fun getRefreshKey(state: PagingState<Int, ShowStoryModel>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowStoryModel> {
    return try {
      withContext(Dispatchers.Default) {
        val position = params.key ?: START_PAGE_INDEX
        Timber.d("Curr postiton: $position")
        val list = mutableListOf<ShowStoryModel>()
        val multipleIds = ids.subList(
          (position * MAX_ITEMS_LIMIT) - 1,
          (position * MAX_ITEMS_LIMIT) - 1 + MAX_ITEMS_LIMIT
        )

        Timber.d("Sublist from : ${((position - 1) * MAX_ITEMS_LIMIT)} to ${(position - 1) * MAX_ITEMS_LIMIT + MAX_ITEMS_LIMIT}")

        Timber.d("List size: " + ids.size)
        Timber.d("fetching : $multipleIds")

        val runningTasks = multipleIds.map { id ->
          async { // this will allow us to run multiple tasks in parallel
            val apiResponse = getItem(id)
            id to apiResponse // associate id and response for later
          }
        }

        val responses = runningTasks.awaitAll()
        responses.forEach { (_, response) ->
          if (response.success) {
            list.add(response.data!!)
          }
        }
//        Timber.d("Res: $list")
        LoadResult.Page(
          data = list,
          prevKey = if (position == START_PAGE_INDEX) null else position - 1,
          nextKey = if (list.isEmpty()) null else position + 1
        )
      }
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }
}
