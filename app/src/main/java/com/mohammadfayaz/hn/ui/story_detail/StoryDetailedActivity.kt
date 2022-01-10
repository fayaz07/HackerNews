package com.mohammadfayaz.hn.ui.story_detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mohammadfayaz.hn.databinding.ActivityStoryDetailedBinding
import com.mohammadfayaz.hn.domain.models.StoryModel
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
  }

  private fun handleIntentData() {
    val storyItem = intent.getParcelableExtra(STORY_DATA) as StoryModel?

    if (storyItem == null) {
      showError(NO_STORY_DATA_AVAILABLE)
      return
    }

    binding.toolbar.title = storyItem.title?.replace("Show HN: ", "")

    registerViewEvents()

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

  private fun startLoading() {
    binding.progressBar.visibility = View.VISIBLE
  }

  private fun stopLoading() {
    binding.progressBar.visibility = View.GONE
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun registerViewEvents() {
    binding.apply {
      webView.apply {
        settings.javaScriptEnabled = true
        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webViewClient = MyWebViewClient()
      }

      toolbar.setNavigationOnClickListener {
        finish()
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
      startLoading()
      Timber.d("onPageStarted $url")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
      stopLoading()
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
