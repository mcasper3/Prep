package io.github.mcasper3.prep.base

interface LceView : View {
    fun showLoading()
    fun hideLoading()
    fun showEmpty()
    fun hideEmpty()

    fun showError(uiModel: FailureUiModel) {
        hideLoading()
    }
}
