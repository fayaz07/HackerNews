package com.mohammadfayaz.hn.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IdsModel(val ids: List<Int>) : Parcelable
