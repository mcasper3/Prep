package io.github.mcasper3.prep.base

import android.support.annotation.StringRes

sealed class UiModel

open class SuccessUiModel : UiModel()

open class InProgressUiModel : UiModel()

open class FailureUiModel(val errorMessage: String? = null, @StringRes val errorMessageResId: Int = 0) : UiModel() {
    fun hasErrorMessage() = errorMessage != null || errorMessageResId == 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FailureUiModel

        if (errorMessage != other.errorMessage) return false
        if (errorMessageResId != other.errorMessageResId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = errorMessage?.hashCode() ?: 0
        result = 31 * result + errorMessageResId
        return result
    }
}
