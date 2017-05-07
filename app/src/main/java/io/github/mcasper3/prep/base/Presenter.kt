package io.github.mcasper3.prep.base

abstract class Presenter<V : View> {
    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun checkIfViewAttached() {
        if (view == null)
            throw IllegalStateException("Must attach view before performing operations")
    }
}
