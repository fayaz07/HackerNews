package com.mohammadfayaz.news.ui.show_stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.news.data.models.ShowStoryModel
import com.mohammadfayaz.news.data.models.StoryModel
import com.mohammadfayaz.news.repository.HackerNewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShowStoriesViewModel @Inject constructor(private val repo: HackerNewsRepo) : ViewModel() {
  val helloWorldText = "Hello from Show Stories View Model"

  private val _liveData = MutableLiveData<String>()
  val liveData: LiveData<String> = _liveData

  private val _liveData2 = MutableLiveData<List<ShowStoryModel>>()
  val liveData2: LiveData<List<ShowStoryModel>> = _liveData2

  fun pullData() {
    viewModelScope.launch {
      val response = repo.pullTopStories()
      _liveData.postValue(response.body().toString())

      val startTime = System.currentTimeMillis()
      Timber.d("Started at: " + System.currentTimeMillis() + " size " + response.body()!!.size)
      withContext(Dispatchers.IO) {
        val list = mutableListOf<ShowStoryModel>()
        val multipleIds = response.body()!!.subList(0, 5)

        val runningTasks = multipleIds.map { id ->
          async { // this will allow us to run multiple tasks in parallel
            val apiResponse = repo.pullShowStoryById(id)
            id to apiResponse // associate id and response for later
          }
        }

        val responses = runningTasks.awaitAll()
        responses.forEach { (id, response) ->
          if (response.isSuccessful) {
            list.add(response.body()!!)
          }
        }
        _liveData2.postValue(list)
        Timber.d("Ended at: " + System.currentTimeMillis())
        Timber.d("Total time: " + (System.currentTimeMillis() - startTime))
//        val result: Deferred<Response<ShowStoryModel>> =   for (i in 0..20) {
//          async {
//            repo.pullShowStoryById(response.body()!![i])
//          }
//        }
//        list.add(result.getCompleted().body()!!)
      }
//      val list = mutableListOf<ShowStoryModel>()
//      for (i in 0..20) {
//        val result = repo.pullShowStoryById(response.body()!![i])
//        list.add(result.body()!!)
//      }
//      _liveData2.postValue(list)
//      Timber.d("Ended at: " + System.currentTimeMillis())
    }
  }
}
