package com.example.prepotency.main.home.container

import android.os.Bundle
import com.example.prepotency.app.api.ConstantPool.Companion.ARG_PARAM_CLASS
import com.example.prepotency.app.api.ConstantPool.Companion.ARG_PARAM_ID
import com.example.prepotency.app.api.ConstantPool.Companion.ARG_PARAM_VIEW
import com.example.prepotency.app.base.viewstratum.fragment.BaseLazyFragment
import com.example.prepotency.test.futile.KotlinBlankFragment
import com.example.prepotency.widght.PassableFloatingActionButton

abstract class HomeContainerFragment :
    BaseLazyFragment<HomeContract.Presenter<HomeContract.View>>(), HomeContract.View {

    private var floatingActionButton: PassableFloatingActionButton? = null
    private var pageId: Int = 0
    protected var pageClass: Int = 0

    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            floatingActionButton =
                it.getSerializable(ARG_PARAM_VIEW) as PassableFloatingActionButton?
            pageId = it.getInt(ARG_PARAM_ID)
            pageClass = it.getInt(ARG_PARAM_CLASS)
        }
    }

    override fun init() {
        presenter.let {
            presenter?.slideShow(pageId)
            if(pageClass==0){
                presenter?.homeAd()
                presenter?.choiceShop()
            }else{
                presenter?.subClass(pageId)
            }
            presenter?.hot(page)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            floatingactionbutton: PassableFloatingActionButton?,
            pageItem: Int,
            pageClass: Int
        ) =
            KotlinBlankFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM_VIEW, floatingactionbutton)
                    putInt(ARG_PARAM_ID, pageItem)
                    putInt(ARG_PARAM_CLASS, pageClass)
                }
            }
    }
}
