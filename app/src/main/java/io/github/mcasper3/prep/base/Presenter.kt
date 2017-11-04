package io.github.mcasper3.prep.base

import io.github.mcasper3.prep.BuildConfig

abstract class Presenter<V : View> {
    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun checkIfViewAttached(operation: () -> Unit) {
        if (BuildConfig.DEBUG && view == null) throw IllegalStateException("Must attach view before performing operations")

        operation.invoke()
    }
}
