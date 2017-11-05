package io.github.mcasper3.prep.base.pager

import android.support.v4.app.Fragment

interface FragmentProvider<T, F : Fragment> {
    fun newInstance(item: T): F
}
