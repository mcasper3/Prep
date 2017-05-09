package io.github.mcasper3.prep.ui.base

import android.os.Bundle
import android.support.v4.util.LongSparseArray
import android.support.v7.app.AppCompatActivity
import io.github.mcasper3.prep.PrepApplication
import io.github.mcasper3.prep.injection.component.ActivityComponent
import io.github.mcasper3.prep.injection.component.ConfigPersistentComponent
import io.github.mcasper3.prep.injection.component.DaggerConfigPersistentComponent
import io.github.mcasper3.prep.injection.module.ActivityModule
import java.util.concurrent.atomic.AtomicLong

open class BaseActivity : AppCompatActivity() {
    private var mActivityComponent: ActivityComponent? = null
    private var mActivityId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (sComponentsArray.get(mActivityId) == null) {
            //Timber.i("Creating new ConfigPersistentComponent id=%d", mActivityId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(PrepApplication.get(this.applicationContext).applicationComponent)
                    .build()
            sComponentsArray.put(mActivityId, configPersistentComponent)
        } else {
            //Timber.i("Reusing ConfigPersistentComponent id=%d", mActivityId)
            configPersistentComponent = sComponentsArray.get(mActivityId)
        }

        mActivityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, mActivityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
           // Timber.i("Clearing ConfigPersistentComponent id=%d", mActivityId)
            sComponentsArray.remove(mActivityId)
        }
        super.onDestroy()
    }

    fun activityComponent(): ActivityComponent {
        return mActivityComponent as ActivityComponent
    }

    companion object {
        private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
        private val sComponentsArray = LongSparseArray<ConfigPersistentComponent>()
    }
}