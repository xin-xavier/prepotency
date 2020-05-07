package com.example.prepotency.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.prepotency.R
import com.example.prepotency.app.api.ConstantPool
import com.example.prepotency.app.base.viewstratum.fragment.BaseFragment
import com.example.prepotency.main.home.TopContract
import com.example.prepotency.widght.PassableFloatingActionButton


class NewsFragment : BaseFragment<TopContract.Presenter<TopContract.View>>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun init() {
    }

    override fun createPresenter(): TopContract.Presenter<TopContract.View>? {
        return null
    }

    companion object {
        @JvmStatic
        fun newInstance(
            floatingactionbutton: PassableFloatingActionButton?
        ) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ConstantPool.ARG_PARAM_VIEW, floatingactionbutton)
                }
            }
    }

}
