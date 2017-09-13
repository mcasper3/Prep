package io.github.mcasper3.prep.util

import android.Manifest
import android.content.pm.PackageManager
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import io.github.mcasper3.prep.base.PrepActivity
import javax.inject.Inject

class PermissionHelper @Inject constructor(private val activity: PrepActivity<*, *>) {
    fun hasPermission(permission: String, requestCode: Int, @StringRes rationale: Int): Boolean {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder(activity)
                        .setMessage(rationale)
                        .setPositiveButton(
                                android.R.string.ok, { _, _ -> ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode) }
                        )
                        .create()
            } else {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
            }

            return false
        }

        return true
    }
}
