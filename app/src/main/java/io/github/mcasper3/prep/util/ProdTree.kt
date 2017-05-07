package io.github.mcasper3.prep.util

import android.util.Log
import timber.log.Timber

class ProdTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }

        // TODO add crash analytics
    }
}