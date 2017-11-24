package io.github.mcasper3.prep.base

import io.github.mcasper3.prep.data.api.FailureUiModel

interface LceView : View {
    fun showLoading()
    fun hideLoading()
    fun showEmpty()
    fun hideEmpty()

    fun showError(uiModel: FailureUiModel) {
        hideLoading()
    }
}
