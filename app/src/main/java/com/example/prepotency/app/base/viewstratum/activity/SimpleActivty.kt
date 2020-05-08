package com.example.prepotency.app.base.viewstratum.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.prepotency.app.AppManager
import com.example.prepotency.app.api.ConstantPool
import com.example.prepotency.app.base.viewstratum.presentation.UIPresentation
import com.example.prepotency.login.LoginActivity
import com.example.prepotency.utils.os.OSStatusBarUtil
import com.jaeger.library.StatusBarUtil
import kotlin.properties.Delegates

abstract class SimpleActivty : LifeLinksBaseActivity() ,
    UIPresentation {

    //protected val TAG: String = if (com.example.decorview.BuildConfig.DEBUG) this.javaClass.simpleName else this.hashCode().toString()

    protected lateinit var activity: Activity
    protected lateinit var context: Context
    protected var statusBarHeight by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        activity = this

        statusBarHeight = getStatusBarHeight(this)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initStatusBar()
        init()
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private fun getStatusBarHeight(context: Context): Int {
        // 获得状态栏高度
        val resourceId =
            context.resources.getIdentifier(
                ConstantPool.IDENTIFIER_NAME,
                ConstantPool.DEF_TYPE,
                ConstantPool.ANDROID
            )
        return context.resources.getDimensionPixelSize(resourceId)
    }

    protected open fun initStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, null)
        OSStatusBarUtil.setImmersiveStatusBar(this, true)
    }

    /**
     * 界面跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(clazz:Class<*>,isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this,clazz))
        }
    }
    /**
     * 携带bundle跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(bundle: Bundle,clazz:Class<*>,isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this, clazz).apply {
                putExtras(bundle)
            })
        }
    }


}