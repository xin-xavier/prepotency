package com.example.prepotency.main.home.container

import android.os.Bundle
import com.example.prepotency.app.api.ConstantPool.Companion.ARG_PARAM_CLASS
import com.example.prepotency.app.api.ConstantPool.Companion.ARG_PARAM_ID
import com.example.prepotency.app.api.ConstantPool.Companion.ARG_PARAM_VIEW
import com.example.prepotency.app.base.viewstratum.fragment.BaseLazyFragment
import com.example.prepotency.futile.KotlinBlankFragment
import com.example.prepotency.widght.PassableFloatingActionButton

abstract class HomeContainerFragment : BaseLazyFragment<ClassContract.Presenter<ClassContract.View>>() , ClassContract.View {

    private var floatingActionButton: PassableFloatingActionButton? = null
    var pageItem: Int? = null
    private var pageClass: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            floatingActionButton =
                it.getSerializable(ARG_PARAM_VIEW) as PassableFloatingActionButton?
            pageItem = it.getInt(ARG_PARAM_ID)
            pageClass = it.getInt(ARG_PARAM_CLASS)
        }
    }

    override fun init() {
        //创建可变集合
        val map= mutableMapOf(
            "cid" to 0,
            "pid" to pageClass,
            "page" to pageClass
        )
        presenter.let { presenter?.classGoods(map) }
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
