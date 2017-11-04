package io.github.mcasper3.prep.data.api

import android.support.annotation.StringRes

open class SuccessUiModel

open class InProgressUiModel

open class FailureUiModel(private val errorMessage: String? = null, @StringRes private val errorMessageResId: Int = 0) {
    fun hasErrorMessage() = errorMessage != null || errorMessageResId == 0
}
