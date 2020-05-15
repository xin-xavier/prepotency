package com.example.prepotency.app.base.viewstratum.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.prepotency.app.AppManager
import com.example.prepotency.app.base.viewstratum.presentation.UIPresentation
import com.example.prepotency.login.LoginActivity

abstract class SimpleFragment : LifeCycleBaseFragment, UIPresentation {

    protected var contentLayoutId = 0
    protected var activity: Activity? = null
    lateinit var rootView: View

    // 视图是否准备好了
    protected var isPrepare = false

    constructor() : super() {}
    constructor(contentLayoutId: Int) : super(contentLayoutId) {
        this.contentLayoutId = contentLayoutId
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            getParameter()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
        isPrepare = true
        init()
    }

    // 获取参数
    protected open fun getParameter() {}

    /**
     * 界面跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(clazz: Class<*>, isLogin: Boolean) {
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        } else {
            startActivity(Intent(context, clazz))
        }
    }

    /**
     * 携带bundle跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(bundle: Bundle, clazz: Class<*>, isLogin: Boolean) {
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        } else {
            startActivity(Intent(context, clazz).apply {
                putExtras(bundle)
            })
        }
    }

}