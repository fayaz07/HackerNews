package com.mohammadfayaz.hn.ui.story_detail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mohammadfayaz.hn.databinding.ActivityStoryDetailedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoryDetailedActivity : AppCompatActivity() {

  private lateinit var binding: ActivityStoryDetailedBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityStoryDetailedBinding.inflate(layoutInflater)

    supportActionBar?.hide()

    setContentView(binding.root)
  }

  companion object {
    fun open(from: Activity) {
      val intent = Intent(from, StoryDetailedActivity::class.java)
      from.startActivity(intent)
    }
  }
}
