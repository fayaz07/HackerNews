package com.mohammadfayaz.hn.utils.icons

import android.content.Context
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome

object AppIcons {
  fun fawComment(context: Context): IconicsDrawable {
    return IconicsDrawable(context, FontAwesome.Icon.faw_comment)
  }

  fun fawHeart(context: Context): IconicsDrawable {
    return IconicsDrawable(context, FontAwesome.Icon.faw_heart)
  }
}
