package io.github.mcasper3.prep.ui.base

abstract class BasePresenter<T : MvpView> {
    protected var view: T? = null

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun checkIfViewAttached() {
        if (view == null) throw IllegalStateException("Must attach view before performing operations")
    }
}