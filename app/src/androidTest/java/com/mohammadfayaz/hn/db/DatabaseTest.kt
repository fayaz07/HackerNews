package com.mohammadfayaz.hn.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.mohammadfayaz.hn.data.sources.local.HackerNewsDB
import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.data.sources.local.source.IdsLocalSource
import com.mohammadfayaz.hn.di.DatabaseModule
import com.mohammadfayaz.hn.domain.models.StoryIdModel
import com.mohammadfayaz.hn.domain.models.StoryType
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Inject
  @Named("fake_db")
  lateinit var database: HackerNewsDB

  private lateinit var idsDao: IdsDao
  private lateinit var idsLocalSource: IdsLocalSource

  @Before
  fun init() {
    hiltRule.inject()

    idsDao = database.idsDao()
    idsLocalSource = IdsLocalSource(idsDao)
  }

  @Test
  fun insertFewStoryIds(): Unit = runBlocking {
    val items = mutableListOf<StoryIdModel>()
    items.add(StoryIdModel(1, StoryType.SHOW))
    items.add(StoryIdModel(2, StoryType.SHOW))
    items.add(StoryIdModel(3, StoryType.SHOW))
    items.add(StoryIdModel(4, StoryType.SHOW))
    items.add(StoryIdModel(5, StoryType.SHOW))
    idsLocalSource.putIds(items)

    val getItems = idsLocalSource.getIdsByStoryType(StoryType.SHOW)
    assertThat(getItems.size).isEqualTo(items.size)
    assertThat(getItems).containsAtLeast(items[0], items[1])
  }

  @After
  fun tearDown() {
    database.close()
  }
}
