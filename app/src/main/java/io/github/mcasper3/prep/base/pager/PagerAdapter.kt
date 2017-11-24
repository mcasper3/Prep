package io.github.mcasper3.prep.base.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import io.github.mcasper3.prep.base.PrepActivity
import io.github.mcasper3.prep.injection.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class PagerAdapter<T, F : Fragment> @Inject constructor(
    activity: PrepActivity<*, *>,
    private val dataProvider: PagerDataProvider<T>,
    private val fragmentProvider: FragmentProvider<T, F>
) : FragmentStatePagerAdapter(activity.getSupportFragmentManager()) {

    override fun getItem(position: Int) = fragmentProvider.newInstance(dataProvider.data[position])

    override fun getCount() = dataProvider.data.size
}
