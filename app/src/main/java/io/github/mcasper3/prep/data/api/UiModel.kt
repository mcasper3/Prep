package io.github.mcasper3.prep.data.api

import android.support.annotation.StringRes

sealed class UiModel

open class SuccessUiModel : UiModel()

open class InProgressUiModel : UiModel()

open class FailureUiModel(val errorMessage: String? = null, @StringRes val errorMessageResId: Int = 0) : UiModel() {
    fun hasErrorMessage() = errorMessage != null || errorMessageResId == 0
}
