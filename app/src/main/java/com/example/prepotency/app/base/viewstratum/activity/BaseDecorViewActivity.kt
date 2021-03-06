package com.example.prepotency.app.base.viewstratum.activity

import com.zs.wanandroid.base.IBasePresenter

abstract class BaseDecorViewActivity<P: IBasePresenter<*>>  : SimpleDecorViewActivity() {

    protected var presenter:P? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        presenter = createPresenter()
        presenter?.let {
            lifecycle.addObserver(it)
        }
    }

    protected abstract fun createPresenter(): P?

}