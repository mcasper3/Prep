package io.github.mcasper3.prep.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<in T> constructor(item: View) : RecyclerView.ViewHolder(item) {
    abstract fun bind(item: T)
}
