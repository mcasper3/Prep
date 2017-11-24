package io.github.mcasper3.prep.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import javax.inject.Inject

class RecyclerAdapter @Inject constructor() : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var viewHolderFactoryMap: Map<Int, ViewHolderFactory<*>> = emptyMap()

    var viewHolderFactories: List<ViewHolderFactory<*>> = listOf()
        set(value) {
            field = value
            viewHolderFactoryMap = value.map { it.getViewType() to it }.toMap()
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int) = viewHolderFactories[position].getViewType()

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) = viewHolderFactories[position].bindViewHolder(holder)

    override fun getItemCount() = viewHolderFactories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        viewHolderFactoryMap.getValue(viewType).createViewHolder(parent)
}
