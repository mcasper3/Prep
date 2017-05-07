package io.github.mcasper3.prep.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

open class PrepActivity<P, V : View> : DaggerAppCompatActivity() where P : Presenter<V> {
    open lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}