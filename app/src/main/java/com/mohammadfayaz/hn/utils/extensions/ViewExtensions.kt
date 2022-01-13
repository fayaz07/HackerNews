package com.mohammadfayaz.hn.utils.extensions

import android.view.View

fun View.gone() {
  this.visibility = View.GONE
}

fun View.hide() {
  this.visibility = View.INVISIBLE
}

fun View.show() {
  this.visibility = View.VISIBLE
}

fun View.showHide(show: Boolean) {
  if (show) this.show() else this.gone()
}
