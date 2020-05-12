@file:Suppress("NAME_SHADOWING")

package com.example.decorview.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.*
import com.example.prepotency.R
import com.example.prepotency.app.api.ConstantPool
import com.example.prepotency.app.api.ConstantTransmit
import com.example.prepotency.app.http.helper.RetrofitHelper
import com.example.prepotency.app.http.observer.HttpDefaultObserver
import com.example.prepotency.app.http.url.ConstantUrl
import com.example.prepotency.app.http.url.DNSConfig
import com.example.prepotency.bean.result.VersionUpdatingResult
import com.example.prepotency.widght.LollipopFixedWebView
import com.example.prepotency.widght.popup.VersionUpdatingPopUp
import com.timmy.tdialog.TDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

object Utils {

    //小米应用商店
    val PACKAGE_MI_MARKET = "com.xiaomi.market"
    val MI_MARKET_PAGE = "com.xiaomi.market.ui.AppDetailActivity"
    //魅族应用商店
    val PACKAGE_MEIZU_MARKET = "com.meizu.mstore"
    val MEIZU_MARKET_PAGE = "com.meizu.flyme.appcenter.activitys.AppMainActivity"
    //VIVO应用商店
    val PACKAGE_VIVO_MARKET = "com.bbk.appstore"
    val VIVO_MARKET_PAGE = "com.bbk.appstore.ui.AppStoreTabActivity"
    //OPPO应用商店
    val PACKAGE_OPPO_MARKET = "com.oppo.market"
    val OPPO_MARKET_PAGE = "a.a.a.aoz"
    //华为应用商店
    val PACKAGE_HUAWEI_MARKET = "com.huawei.appmarket"
    val HUAWEI_MARKET_PAGE =
        "com.huawei.appmarket.service.externalapi.view.ThirdApiActivity"
    //ZTE应用商店
    val PACKAGE_ZTE_MARKET = "zte.com.market"
    val ZTE_MARKET_PAGE = "zte.com.market.view.zte.drain.ZtDrainTrafficActivity"
    //360手机助手
    val PACKAGE_360_MARKET = "com.qihoo.appstore"
    val PACKAGE_360_PAGE = "com.qihoo.appstore.distribute.SearchDistributionActivity"
    //酷市场 -- 酷安网
    val PACKAGE_COOL_MARKET = "com.coolapk.market"
    val COOL_MARKET_PAGE = "com.coolapk.market.activity.AppViewActivity"
    //应用宝
    val PACKAGE_TENCENT_MARKET = "com.tencent.android.qqdownloader"
    val TENCENT_MARKET_PAGE = "com.tencent.pangu.link.LinkProxyActivity"
    //PP助手
    val PACKAGE_ALI_MARKET = "com.pp.assistant"
    val ALI_MARKET_PAGE = "com.pp.assistant.activity.MainActivity"
    //豌豆荚
    val PACKAGE_WANDOUJIA_MARKET = "com.wandoujia.phoenix2"
    // 低版本可能是 com.wandoujia.jupiter.activity.DetailActivity
    val WANDOUJIA_MARKET_PAGE = "com.pp.assistant.activity.PPMainActivity"
    //UCWEB
    val PACKAGE_UCWEB_MARKET = "com.UCMobile"
    val UCWEB_MARKET_PAGE = "com.pp.assistant.activity.PPMainActivity"

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

    // 版本更新
    fun versionUpdating(context: Context) {
        val map: MutableMap<String, String> =
            HashMap()
        map["model"] = DeviceUtils.getManufacturer()
        map["version"] = AppUtils.getAppVersionName()
        RetrofitHelper.getApiService()
            .versionUpdating(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<VersionUpdatingResult>() {
                override fun disposable(d: Disposable) {
                }
                override fun onSuccess(t: VersionUpdatingResult) {
                    val enforce: Int = t.getEnforce()
                    val content: String = t.getContent()
                    if (enforce == 0) {
                        // 强制
                        val versionUpdatingPopUp = VersionUpdatingPopUp(context)
                        // 点击popupwindow背景部分不想让popupwindow隐藏怎么办
                        versionUpdatingPopUp.setOutSideDismiss(false)
                        // 如何点击back键不关闭BasePopup
                        versionUpdatingPopUp.setBackPressEnable(false)
                        versionUpdatingPopUp.setContent(content)
                        versionUpdatingPopUp.showPopupWindow()
                    } else if (enforce == 1) {
                        val versionUpdatingPopUp =
                            VersionUpdatingPopUp(context)
                        versionUpdatingPopUp.setContent(content)
                        versionUpdatingPopUp.showPopupWindow()
                    }
                }
                override fun onError(errorMsg: String) {
                    ToastUtils.showShort(errorMsg)
                }
            })
    }

    // 进入应用市场详情页
    fun goMarket(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(
            "market://details?id=" + AppUtils.getAppPackageName()
        )
        val keys = getKeys(context)
        if (keys != null) {
            intent.setClassName(keys[0]!!, keys[1]!!)
        }
        //修复某些老手机会因为找不到任何市场而报错
        if (IntentUtils.isIntentAvailable(intent)) {
            context.startActivity(intent)
        } else {
            ToastUtils.showShort("您没有安装应用商店")
        }
    }

    // 获取Keys
    private fun getKeys(context: Context): Array<String?>? {
        val keys = arrayOfNulls<String>(2)
        if (isPackageExist(context, PACKAGE_MI_MARKET)) {
            keys[0] = PACKAGE_MI_MARKET
            keys[1] = MI_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_VIVO_MARKET)) {
            keys[0] = PACKAGE_VIVO_MARKET
            keys[1] = VIVO_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_OPPO_MARKET)) {
            keys[0] = PACKAGE_OPPO_MARKET
            keys[1] = OPPO_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_HUAWEI_MARKET)) {
            keys[0] = PACKAGE_HUAWEI_MARKET
            keys[1] = HUAWEI_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_ZTE_MARKET)) {
            keys[0] = PACKAGE_ZTE_MARKET
            keys[1] = ZTE_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_COOL_MARKET)) {
            keys[0] = PACKAGE_COOL_MARKET
            keys[1] = COOL_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_360_MARKET)) {
            keys[0] = PACKAGE_360_MARKET
            keys[1] = PACKAGE_360_PAGE
        } else if (isPackageExist(context, PACKAGE_MEIZU_MARKET)) {
            keys[0] = PACKAGE_MEIZU_MARKET
            keys[1] = MEIZU_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_TENCENT_MARKET)) {
            keys[0] = PACKAGE_TENCENT_MARKET
            keys[1] = TENCENT_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_ALI_MARKET)) {
            keys[0] = PACKAGE_ALI_MARKET
            keys[1] = ALI_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_WANDOUJIA_MARKET)) {
            keys[0] = PACKAGE_WANDOUJIA_MARKET
            keys[1] = WANDOUJIA_MARKET_PAGE
        } else if (isPackageExist(context, PACKAGE_UCWEB_MARKET)) {
            keys[0] = PACKAGE_UCWEB_MARKET
            keys[1] = UCWEB_MARKET_PAGE
        }
        return if (TextUtils.isEmpty(keys[0])) {
            null
        } else {
            keys
        }
    }

    // 判断package是否存在
    fun isPackageExist(
        context: Context,
        packageName: String?
    ): Boolean {
        val manager = context.packageManager
        val intent = Intent().setPackage(packageName)
        @SuppressLint("WrongConstant") val infos =
            manager.queryIntentActivities(
                intent,
                PackageManager.GET_INTENT_FILTERS
            )
        return !(infos.size < 1)
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
                .setDimAmount(ConstantPool.TD_DIM_AMOUNT)
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