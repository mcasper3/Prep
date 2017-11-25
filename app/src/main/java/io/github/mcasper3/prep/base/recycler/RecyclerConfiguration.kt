package io.github.mcasper3.prep.base.recycler

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes

class RecyclerConfiguration(
    @StringRes val emptyStateTextResId: Int,
    @StringRes val errorStateTextResId: Int,
    @DrawableRes val emptyStateImageResId: Int,
    val isSwipeRefreshEnabled: Boolean,
    @LayoutRes val topLayoutResId: Int
)
