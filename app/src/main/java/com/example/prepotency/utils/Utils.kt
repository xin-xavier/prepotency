@file:Suppress("NAME_SHADOWING")

package com.example.decorview.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.example.prepotency.R
import com.example.prepotency.app.api.ConstantPool
import com.example.prepotency.app.api.ConstantTransmit
import com.example.prepotency.app.http.url.ConstantUrl
import com.example.prepotency.app.http.url.DNSConfig
import com.example.prepotency.widght.LollipopFixedWebView
import com.timmy.tdialog.TDialog

object Utils {
    @Volatile
    private var singleton: Utils? = null
    val instance: Utils?
        get() {
            if (singleton == null) {
                synchronized(Utils::class.java) {
                    if (singleton == null) {
                        singleton = Utils
                    }
                }
            }
            return singleton
        }

    fun showUserAgreementDialog(context: Context, fragmentManager: FragmentManager) {
        if (!SPStaticUtils.getBoolean(ConstantTransmit.USER_AGREEMENT)) {
            val view = View.inflate(context, R.layout.dialog_user_agreement, null)
            val px_width: Int = ConvertUtils.dp2px(ConstantPool.DUP_WIDTH.toFloat())
            val px_height: Int = ConvertUtils.dp2px(ConstantPool.DUP_HEIGHT.toFloat())
            val webView: LollipopFixedWebView = view.findViewById(R.id.webView)
            //声明WebSettings子类
            //val webSettings: WebSettings = webView.getSettings()
            //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
            //webSettings.javaScriptEnabled = true
            // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
            // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
            //支持插件
            //webSettings.setPluginsEnabled(true);
            webView.loadUrl(
                DNSConfig.getInstance().apiServerUrl +
                        ConstantUrl.variousPrivacy
            )
            // 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            }
            TDialog.Builder(fragmentManager)
                .setDialogView(view)
                .setGravity(Gravity.CENTER)
                .setWidth(px_width)
                .setHeight(px_height)
                .setDimAmount(ConstantPool.TD_DIM_AMOUNT.toFloat())
                .setCancelableOutside(true)
                .setOnDismissListener { }
                .addOnClickListener(R.id.disagree, R.id.agree)
                .setOnViewClickListener { _, view, tDialog ->
                    when (view.id) {
                        R.id.disagree -> tDialog.dismiss()
                        R.id.agree -> {
                            SPStaticUtils.put(
                                ConstantTransmit.USER_AGREEMENT,
                                true
                            )
                            tDialog.dismiss()
                        }
                    }
                }
                .create()
                .show()
        }
    }


}