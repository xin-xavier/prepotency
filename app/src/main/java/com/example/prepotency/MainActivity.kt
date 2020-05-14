package com.example.prepotency

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.RomUtils
import com.example.decorview.utils.Utils
import com.example.prepotency.app.api.ConstantPool
import com.example.prepotency.app.base.viewstratum.activity.SimpleDecorViewActivity
import com.example.prepotency.bean.entity.TabEntity
import com.example.prepotency.main.HomeFragment
import com.example.prepotency.main.MineFragment
import com.example.prepotency.main.NewsFragment
import com.example.prepotency.main.ShoppingCartFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_actionbar_view.*
import java.util.*

class MainActivity : SimpleDecorViewActivity() {

    private val tabEntities = ArrayList<CustomTabEntity>()

    private var pressedTime: Long = 0
    private var pageItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun init() {
        Utils.instance?.showUserAgreementDialog(context,supportFragmentManager)

        initData()

        settingUpTheNavigator()

        // 检查版本更新
        //Utils.instance?.versionUpdating(this)
    }

    override fun onPrepare() {
        if (isDefaultBar()) {
            val returnPager =
                toolbarManagerFragment.rootView.findViewById<ImageView>(R.id.returnPager)
            val toolbarTitle =
                toolbarManagerFragment.rootView.findViewById<TextView>(R.id.toolbarTitle)
            toolbarTitle.text = ConstantPool.TABS[pageItem]
            returnPager.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun layoutToolbarResID(): Int {
        when (pageItem) {
            0 -> {
                appbarBg.setImageResource(R.color.initial)
                return R.layout.layout_searchbox_btn
            }
            else -> {
                appbarBg.setImageResource(R.color.white)
                return R.layout.layout_init_toobar_view
            }
        }
    }

    private fun initData() {
        for (tab in ConstantPool.TABS) {
            val indexOf = ConstantPool.TABS.indexOf(tab);
            tabEntities.add(
                TabEntity(
                    tab,
                    ConstantPool.ICON_SELECT_IDS[indexOf],
                    ConstantPool.ICON_UNSELECT_IDS[indexOf]
                )
            )
        }
    }

    private fun settingUpTheNavigator() {
        val pagerAdapter = TabsPagerAdapter2(this)
        carousel.adapter = pagerAdapter
        carousel.isUserInputEnabled = false;
        navigator.setTabData(tabEntities)

        navigator.setOnTabSelectListener(object : OnTabSelectListener {
            // 点击时运行
            override fun onTabSelect(position: Int) {
                carousel.currentItem = position
            }
            // 选项卡选中时运行
            override fun onTabReselect(position: Int) {}
        })

        // viewpager2
        carousel.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.i(TAG, "onPageSelected position = " + position)
                if (position in 2..3) {
                    floatingActionButton.visibility = View.INVISIBLE
                }
                pageItem = position
                navigator.currentTab = position
                setToolbarResID(layoutToolbarResID())
            }
        })

    }

    override fun onBackPressed() {
        if (carousel.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            val nowTime = System.currentTimeMillis() //获取第一次按键时间
            if (nowTime - pressedTime > 2000) { //比较两次按键时间差
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                pressedTime = nowTime
            } else { //退出程序
                /*finish()
                System.exit(0)*/
                super.onBackPressed()
            }
        } else {
            // Otherwise, select the previous step.
            carousel.currentItem = 0
        }
    }

    private inner class TabsPagerAdapter2(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = ConstantPool.TABS.size
        override fun createFragment(position: Int): Fragment =
            when (position) {
                0 -> {
                    HomeFragment.newInstance(floatingActionButton)
                }
                1 -> {
                    NewsFragment.newInstance(floatingActionButton)
                }
                2 -> {
                    ShoppingCartFragment.newInstance(floatingActionButton)
                }
                3 -> {
                    MineFragment.newInstance(floatingActionButton)
                }
                else -> {
                    HomeFragment.newInstance(floatingActionButton)
                }
            }

    }



}
