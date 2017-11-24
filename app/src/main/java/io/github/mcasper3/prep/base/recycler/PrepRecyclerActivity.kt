package io.github.mcasper3.prep.base.recycler

import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.LceView
import io.github.mcasper3.prep.base.PrepActivity
import io.github.mcasper3.prep.base.Presenter
import io.github.mcasper3.prep.data.api.FailureUiModel
import kotterknife.bindOptionalView
import kotterknife.bindView
import javax.inject.Inject

abstract class PrepRecyclerActivity<P, V> : PrepActivity<P, V>(), LceView where P : Presenter<V>, V : LceView {

    @Inject protected lateinit var adapter: RecyclerAdapter
    @Inject protected lateinit var configuration: RecyclerConfiguration

    protected val emptyStateText: TextView? by bindOptionalView(R.id.empty_state_text)
    protected val toolbar: Toolbar? by bindOptionalView(R.id.toolbar)
    protected val emptyStateImage: ImageView? by bindOptionalView(R.id.empty_state_image)

    private val recyclerView: RecyclerView by bindView(R.id.recycler_view)
    private val fab: FloatingActionButton? by bindView(R.id.fab)
    private val swipeRefreshLayout: SwipeRefreshLayout? by bindView(R.id.swipe_refresh)
    private val loadingView: View? by bindOptionalView(R.id.loading)

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
        swipeRefreshLayout?.setOnRefreshListener { onSwipeRefresh() }
        swipeRefreshLayout?.setColorSchemeResources(R.color.colorAccent)
        loadingView?.visibility = View.VISIBLE
        fab?.setOnClickListener { onFabClicked() }

        emptyStateImage?.setImageResource(configuration.emptyStateImageResId)
    }

    override fun showLoading() {
        loadingView?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView?.visibility = View.GONE
        swipeRefreshLayout?.isRefreshing = false
    }

    override fun showEmpty() {
        hideLoading()
        adapter.viewHolderFactories = emptyList()
        emptyStateImage?.visibility = View.VISIBLE
        emptyStateText?.visibility = View.VISIBLE
        emptyStateText?.setText(configuration.emptyStateTextResId)
    }

    override fun showError(uiModel: FailureUiModel) {
        showEmpty()
        emptyStateText?.setText(configuration.errorStateTextResId)
    }

    override fun hideEmpty() {
        recyclerView.visibility = View.VISIBLE
        emptyStateImage?.visibility = View.GONE
        emptyStateText?.visibility = View.GONE
    }

    open fun onSwipeRefresh() {
    }

    open fun onFabClicked() {
    }
}
