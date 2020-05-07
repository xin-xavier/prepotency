package com.example.prepotency.app.base.viewstratum.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import com.zs.wanandroid.base.IBasePresenter

// dubug 阶段继承 LifeCycleBaseFragment 便于测试
// release 阶段可以直接继承 Fragment, UIPresentation 关闭相应的 log
abstract class BaseFragment<P: IBasePresenter<*>> : SimpleFragment {

    protected var presenter:P? = null

    constructor(): super() {}
    constructor(contentLayoutId: Int): super(contentLayoutId) {
        this.contentLayoutId=contentLayoutId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter?.let { lifecycle.addObserver(it) }
    }

    protected abstract fun createPresenter(): P?

}