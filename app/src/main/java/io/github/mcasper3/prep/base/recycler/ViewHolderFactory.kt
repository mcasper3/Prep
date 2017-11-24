package io.github.mcasper3.prep.base.recycler

import android.view.ViewGroup

abstract class ViewHolderFactory<T>(protected val item: T) {
    abstract fun createViewHolder(parent: ViewGroup): BaseViewHolder<T>
    abstract fun bind(viewHolder: BaseViewHolder<T>)

    fun bindViewHolder(viewHolder: BaseViewHolder<*>) {
        @Suppress("UNCHECKED_CAST")
        bind(viewHolder as BaseViewHolder<T>)
    }

    fun getViewType() = javaClass.name.hashCode()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ViewHolderFactory<*>

        if (item != other.item) return false

        return true
    }

    override fun hashCode() = item?.hashCode() ?: 0

}