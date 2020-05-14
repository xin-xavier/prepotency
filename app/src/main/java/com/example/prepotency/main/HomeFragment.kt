package com.example.prepotency.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.prepotency.main.home.container.HomeContainerFragment
import com.example.prepotency.R
import com.example.prepotency.app.api.ConstantPool
import com.example.prepotency.app.base.viewstratum.fragment.BaseFragment
import com.example.prepotency.bean.result.TopClassResult
import com.example.prepotency.main.home.TopContract
import com.example.prepotency.main.home.TobPresenter
import com.example.prepotency.widght.PassableFloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

class HomeFragment : BaseFragment<TopContract.Presenter<TopContract.View>>() ,TopContract.View{

    private var floatingActionButton: PassableFloatingActionButton? = null

    override fun getParameter() {
        super.getParameter()
        floatingActionButton = arguments?.getSerializable(ConstantPool.ARG_PARAM_VIEW) as PassableFloatingActionButton?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun init() {
        floatingActionButton?.let { presenter?.topClass() }
    }

    override fun showList(list: MutableList<TopClassResult>) {
        Log.i(TAG,list.toString())
        showTopClass(list)
    }

    private fun showTopClass(list: MutableList<TopClassResult>) {
        val pagerAdapter: BlankPagerAdapter2 = BlankPagerAdapter2(this,list)
        viewPager.adapter = pagerAdapter
        val commonNavigator = CommonNavigator(context)
        commonNavigator.isAdjustMode = false
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            @SuppressLint("InflateParams")
            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val commonPagerTitleView = CommonPagerTitleView(context)
                val customLayout: View = layoutInflater.inflate(R.layout.navigator_item, null)
                val title = customLayout.findViewById<TextView>(R.id.title)
                val indicator = customLayout.findViewById<ImageView>(R.id.indicator)
                //title.setText(RUNOOB[index])
                title.text = list[index].name
                commonPagerTitleView.setContentView(customLayout)
                commonPagerTitleView.onPagerTitleChangeListener =
                    object : CommonPagerTitleView.OnPagerTitleChangeListener {

                        override fun onSelected(index: Int, totalCount: Int) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                title.setTextAppearance(R.style.SkinCompatTextAppearance_titleSelectColor)
                            } else {
                                title.setTextAppearance(
                                    context,
                                    R.style.SkinCompatTextAppearance_titleSelectColor
                                )
                            }
                            indicator.visibility = View.VISIBLE
                        }

                        override fun onDeselected(index: Int, totalCount: Int) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                title.setTextAppearance(R.style.SkinCompatTextAppearance_titleUnSelectColor)
                            } else {
                                title.setTextAppearance(
                                    context,
                                    R.style.SkinCompatTextAppearance_titleSelectColor
                                )
                            }
                            indicator.visibility = View.GONE
                        }

                        override fun onLeave(
                            index: Int,
                            totalCount: Int,
                            leavePercent: Float,
                            leftToRight: Boolean
                        ) {
                            title.setScaleX(1.4f + (1.1f - 1.4f) * leavePercent)
                            title.setScaleY(1.4f + (1.1f - 1.4f) * leavePercent)
                        }

                        override fun onEnter(
                            index: Int,
                            totalCount: Int,
                            enterPercent: Float,
                            leftToRight: Boolean
                        ) {
                            title.setScaleX(1.1f + (1.4f - 1.1f) * enterPercent)
                            title.setScaleY(1.1f + (1.4f - 1.1f) * enterPercent)
                        }
                    }

                commonPagerTitleView.setOnClickListener {
                    viewPager.currentItem = index
                }

                return commonPagerTitleView
            }

            override fun getCount(): Int {
                return list.size
            }

            override fun getIndicator(context: Context?): IPagerIndicator? {
                return null
            }

        }
        magicIndicator.setNavigator(commonNavigator)
        //ViewPagerHelper.bind(magicIndicator,viewPager)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                magicIndicator.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                magicIndicator.onPageSelected(position)
            }
        })
        //viewPager.offscreenPageLimit = 6
    }

    override fun showError(error: String) {
        Log.e(TAG, "onError: $error");
    }

    private inner class BlankPagerAdapter2(
        fragmentActivity: HomeFragment,
        var list: MutableList<TopClassResult>
    ) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): HomeContainerFragment =
            HomeContainerFragment.newInstance(floatingActionButton, list.get(position).id, position)
        override fun getItemCount(): Int = list.size
    }

    override fun createPresenter(): TopContract.Presenter<TopContract.View>? {
        return TobPresenter(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(
            floatingactionbutton: PassableFloatingActionButton?
        ) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ConstantPool.ARG_PARAM_VIEW, floatingactionbutton)
                }
            }
    }

}