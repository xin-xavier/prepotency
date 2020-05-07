package com.example.prepotency

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.SPStaticUtils
import com.example.prepotency.app.api.ConstantPool.Companion.PUSH_STATUS
import com.example.prepotency.app.http.helper.GameHttpClient
import com.example.prepotency.app.notification.NotificationChannels
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

class GameApplacation : Application() {

    private var refWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        refWatcher = setupLeakCanary()

        if (!SPStaticUtils.contains(PUSH_STATUS)) {
            SPStaticUtils.put(PUSH_STATUS, true)
        }

        GameHttpClient.getInstance().init(instance)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannels.createAllNotificationChannels(instance)
        }
    }

    // Error: null, Cannot fit requested classes in a single dex file (# methods: 66116 > 65536)
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    companion object {
        lateinit var instance: GameApplacation
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val treeApplication =
                context.applicationContext as GameApplacation
            return treeApplication.refWatcher
        }

    }
}