package com.mingqing.autodrone

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class AutoDroneApp : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        applicationScope = CoroutineScope(SupervisorJob())
        packageInfo = packageManager.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getPackageInfo(context.packageName, PackageManager.PackageInfoFlags.of(0))
            } else {
                getPackageInfo(context.packageName, 0)
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var applicationScope: CoroutineScope
        lateinit var packageInfo: PackageInfo
    }

}