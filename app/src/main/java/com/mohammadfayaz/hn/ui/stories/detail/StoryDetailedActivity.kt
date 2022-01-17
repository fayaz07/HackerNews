package com.mohammadfayaz.hn.ui.stories.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.mohammadfayaz.hn.R
import com.mohammadfayaz.hn.databinding.ActivityStoryDetailedBinding
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.utils.ViewEvent
import com.mohammadfayaz.hn.utils.extensions.gone
import com.mohammadfayaz.hn.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class StoryDetailedActivity : AppCompatActivity() {

  private lateinit var binding: ActivityStoryDetailedBinding
  private val viewModel: StoryDetailedViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityStoryDetailedBinding.inflate(layoutInflater)

    supportActionBar?.hide()

    setContentView(binding.root)

    handleIntentData()
    addObservers()
  }

  private fun addObservers() {
    viewModel.viewEvent.observe(this) {
      when (it) {
        is ViewEvent.Error<*> -> {
        }
        ViewEvent.Idle -> {
        }
        ViewEvent.Loading -> {
        }
        is ViewEvent.Success<*> -> {
          handleViewModelSuccessEvents(it)
        }
      }
    }
  }

  private fun handleViewModelSuccessEvents(it: ViewEvent.Success<*>) {
    when (it.code) {
      FAVOURITE_EVENT -> {
        binding.floatingActionButton.setImageDrawable(
          ContextCompat.getDrawable(
            this,
            if (it.data as Boolean) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
          )
        )
      }
    }
  }

  private fun handleIntentData() {
    val storyItem = intent.getParcelableExtra(STORY_DATA) as StoryModel?

    if (storyItem == null) {
      showError(NO_STORY_DATA_AVAILABLE)
      return
    }

    binding.toolbar.title = storyItem.title
    viewModel.isFavourite(storyItem.id)

    registerViewEvents(storyItem)

    loadWebPage(storyItem)
  }

  private fun showError(msg: String) {
    Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
  }

  private fun loadWebPage(storyItem: StoryModel) {
    if (storyItem.url != null) {
      binding.webView.apply {
        loadUrl(storyItem.url)
      }
    }
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun registerViewEvents(storyItem: StoryModel) {
    binding.apply {
      webView.apply {
        settings.javaScriptEnabled = true
        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webViewClient = MyWebViewClient()
      }

      toolbar.setNavigationOnClickListener {
        finish()
      }

      floatingActionButton.setOnClickListener {
        viewModel.saveUnSaveAsFavourite(storyItem.id, storyItem.storyType)
      }
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    Timber.d("Saving state")
    binding.webView.saveState(outState)
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    Timber.d("Restoring state")
    binding.webView.restoreState(savedInstanceState)
  }

  companion object {
    const val FAVOURITE_EVENT = 1
    private const val STORY_DATA = "story_data"
    private const val NO_STORY_DATA_AVAILABLE = "Invalid story data/no story data available"

    fun open(from: Activity, storyItem: StoryModel) {
      val intent = Intent(from, StoryDetailedActivity::class.java)
      intent.putExtra(STORY_DATA, storyItem)
      from.startActivity(intent)
    }
  }

  inner class MyWebViewClient : WebViewClient() {
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
      super.onPageStarted(view, url, favicon)
      binding.progressBar.show()
      Timber.d("onPageStarted $url")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
      binding.progressBar.gone()
      super.onPageFinished(view, url)
      Timber.d("onPageFinished")
    }

    override fun onReceivedError(
      view: WebView?,
      request: WebResourceRequest?,
      error: WebResourceError?
    ) {
      super.onReceivedError(view, request, error)
      showError("We are unable to load the webpage ${request?.url} ")
    }
  }
}
