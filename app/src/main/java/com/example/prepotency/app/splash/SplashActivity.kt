package com.example.prepotency.app.splash

import android.os.Bundle
import com.blankj.utilcode.util.SPStaticUtils
import com.example.prepotency.MainActivity
import com.example.prepotency.R
import com.example.prepotency.app.api.ConstantTransmit.Companion.SPLASH
import com.example.prepotency.app.base.viewstratum.activity.SimpleActivty
import com.example.prepotency.test.AutoActivity
import kotlinx.android.synthetic.main.activity_splash.*


@Suppress("NAME_SHADOWING")
class SplashActivity : SimpleActivty() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun init() {
        imageView.postDelayed(
            Runnable {
                if (SPStaticUtils.getBoolean(SPLASH)) {
                    intent(MainActivity::class.java)
                    //intent(AutoActivity::class.java)
                } else {
                    SPStaticUtils.put(SPLASH, true)
                    intent(GuideActivity::class.java)
                }
                finish()
            },
            1000
        )
    }
}
