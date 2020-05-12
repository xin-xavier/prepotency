package com.example.prepotency

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.example.prepotency.app.api.ConstantPool.Companion.DUP_HEIGHT
import com.example.prepotency.app.api.ConstantPool.Companion.DUP_WIDTH
import com.example.prepotency.app.api.ConstantPool.Companion.TD_DIM_AMOUNT
import com.example.prepotency.app.api.ConstantTransmit
import com.example.prepotency.app.api.ConstantTransmit.Companion.SPLASH
import com.example.prepotency.app.base.viewstratum.activity.SimpleActivty
import com.example.prepotency.app.http.url.ConstantUrl
import com.example.prepotency.app.http.url.DNSConfig
import com.example.prepotency.test.ToolbarEditActivity
import com.example.prepotency.widght.LollipopFixedWebView
import com.timmy.tdialog.TDialog
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
