package io.github.mcasper3.recipevision.ui.base

interface LceView : MvpView {
    fun showLoading()
    fun hideLoading()
    fun showError()
}