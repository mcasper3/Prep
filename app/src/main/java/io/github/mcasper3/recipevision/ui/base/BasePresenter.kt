package io.github.mcasper3.recipevision.ui.base

abstract class BasePresenter<T : MvpView> {
    protected var view: T? = null

    fun attachView(view: T) {
        this.view = view
    }

    fun detatchView() {
        view = null
    }
}