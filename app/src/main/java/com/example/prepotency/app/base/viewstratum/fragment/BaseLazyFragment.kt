package com.example.prepotency.app.base.viewstratum.fragment

import com.example.prepotency.app.base.viewstratum.presentation.UIPresentationLazyLoad
import com.zs.wanandroid.base.IBasePresenter


abstract class BaseLazyFragment<P : IBasePresenter<*>>() : BaseFragment<P>(),
    UIPresentationLazyLoad {

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

}