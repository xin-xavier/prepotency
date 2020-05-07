package com.example.prepotency.app.base.viewstratum.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import com.blankj.utilcode.util.SizeUtils
import com.example.prepotency.R
import com.example.prepotency.app.api.ConstantPool
import com.example.prepotency.app.base.viewstratum.ToolbarManagerFragment
import com.example.prepotency.app.base.viewstratum.ToolbarManagerFragment.OnPrepareListener
import kotlinx.android.synthetic.main.layout_actionbar_view.*


abstract class SimpleDecorViewActivity : SimpleActivty(), OnPrepareListener {

    protected lateinit var inflater: LayoutInflater
    protected lateinit var parentLinearLayout: LinearLayout
    protected lateinit var beginTransaction: FragmentTransaction
    protected lateinit var toolbarManagerFragment: ToolbarManagerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView(initLayoutRes())
    }

    /**
     * @param layoutResID layout id of sub-activity
     */
    override fun setContentView(@LayoutRes layoutResID: Int) { //  added the sub-activity layout id in parentLinearLayout
        inflater.inflate(layoutResID, parentLinearLayout, true)
        initStatusBar()
        init()
    }

    open fun initLayoutRes(): Int {
        return R.layout.layout_decor_view
    }

    /**
     * @param layoutResID layout id of sub-activity
     */
    private fun initContentView(@LayoutRes layoutResID: Int) {
        val viewGroup = findViewById(android.R.id.content) as ViewGroup
        viewGroup.removeAllViews()
        parentLinearLayout = LinearLayout(this)
        parentLinearLayout.orientation = LinearLayout.VERTICAL
        //  add parentLinearLayout in viewGroup
        viewGroup.addView(parentLinearLayout)
        //  add the layout of BaseDecorContentActivity in parentLinearLayout
        inflater = LayoutInflater.from(this)
        inflater.inflate(layoutResID, parentLinearLayout, true)

        // toolbarLayoutContent 一般为 40dp
        // 通过LayoutParams获取 这是获取的就是在 xml 里明确定义的高度
        val height = toolbarLayoutContent.layoutParams.height
        // 此时在 xml 设置 width 为 match_parent 而获取到的是 -1
        // xincaution 当没有设置具体宽高的获取到的值为0
        //val width = toolbarLayoutContent.layoutParams.width
        //Log.i(TAG, "onCreate: height = " + height + " == " + SizeUtils.dp2px(ConstantPool._40.toFloat()))
        val appbarHeight: Int = statusBarHeight + SizeUtils.dp2px(ConstantPool._0.toFloat()) + height
        appbarLayout.getLayoutParams().height = appbarHeight

        setToolbarResID(layoutToolbarResID())
    }

    open fun setToolbarResID(@LayoutRes layoutToolbarID: Int) {
        beginTransaction = supportFragmentManager.beginTransaction()
        toolbarManagerFragment =
            ToolbarManagerFragment(
                layoutToolbarID,
                this
            )
        beginTransaction.replace(R.id.toolbarLayoutContent, toolbarManagerFragment)
        beginTransaction.commit()
    }

    open fun layoutToolbarResID(): Int {
        return R.layout.layout_init_toobar_view
    }

    open fun isDefaultBar(): Boolean {
        return layoutToolbarResID() == R.layout.layout_init_toobar_view
    }

    open fun setTitle(title: String) {
        if (isDefaultBar()) {
            val toolbarTitle = toolbarManagerFragment.rootView.findViewById<TextView>(R.id.toolbarTitle)
            toolbarTitle.text = title
        }
    }

}