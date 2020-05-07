package com.example.prepotency.app.base.viewstratum.fragment

import com.zs.wanandroid.base.IBasePresenter

abstract class LazyFragment() : SimpleFragment() {

    protected var isFirstVisible = true

    override fun onResume() {
        super.onResume()
        if(isFirstVisible){
            isFirstVisible=false
            onLazyLoad()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isFirstVisible=true
    }

    protected open fun onLazyLoad(){}

}