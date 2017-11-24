package io.github.mcasper3.prep.base.recycler

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

class RecyclerConfiguration(
    @StringRes val emptyStateTextResId: Int = 0,
    @StringRes val errorStateTextResId: Int = 0,
    @DrawableRes val emptyStateImageResId: Int = 0
)
