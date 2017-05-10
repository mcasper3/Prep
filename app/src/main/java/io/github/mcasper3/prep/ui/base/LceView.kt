package io.github.mcasper3.prep.ui.base

interface LceView : MvpView {
    fun showLoading()
    fun hideLoading()
    fun showEmpty()
    fun showError()
}