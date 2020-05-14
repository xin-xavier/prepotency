package com.example.prepotency.test.futile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.prepotency.R
import com.example.prepotency.adapter.pager.BannerImageAdapter
import com.example.prepotency.app.api.ConstantPool.Companion.YANG
import com.example.prepotency.app.api.ConstantPool.Companion.peoplePay
import com.example.prepotency.bean.result.*
import com.example.prepotency.main.home.container.HomeContainerFragment
import com.example.prepotency.main.home.container.HomeContract
import com.example.prepotency.main.home.container.HomePresenter
import com.example.prepotency.utils.GlideEngineLoging
import com.example.prepotency.utils.tool.RecyclerViewSpacesItemDecoration
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.fragment_kotlin_blank.*


class KotlinBlankFragment : HomeContainerFragment() {

    private val rowsList = ArrayList<HotResult.RowsBean>()
    private val adapter = ProductListAdapter(R.layout.commodity_itme, rowsList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kotlin_blank, container, false)
    }

    override fun init() {
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val stringIntegerHashMap: HashMap<String, Int?> = HashMap()
        stringIntegerHashMap[RecyclerViewSpacesItemDecoration.TOP_DECORATION] =
            SizeUtils.sp2px(12.toFloat())
        recyclerView.addItemDecoration(RecyclerViewSpacesItemDecoration(stringIntegerHashMap))
        recyclerView.adapter = adapter
        super.init()
    }

    override fun onLazyLoad() {
        recyclerView.scrollToPosition(0)
    }

    override fun showHot(hotResult: HotResult) {
        val rows = hotResult.rows
        rowsList.clear()
        rowsList.addAll(rows)
        adapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        ToastUtils.showShort(error)
    }

    override fun showSlideShow(slideList: List<SlideShowResult>) {
        adapter.setHeaderView(getHeaderView(slideList), 1)
    }

    override fun showAd(adList: List<HomeAdResult>) {
        adapter.setHeaderView(getAdHeaderView(adList), 2)
    }

    override fun showSubClass(subclassList: List<SubClassResult>) {
    }

    override fun showChoiceShop(choiceLists: List<List<ChoiceShopResult>>) {
    }

    private fun getHeaderView(slideList: List<SlideShowResult>): View {
        val view = View.inflate(context, R.layout.banner, null)
        val imageAdapter = BannerImageAdapter(slideList);
        val banner =
            view.findViewById<Banner<SlideShowResult, BannerAdapter<SlideShowResult, BannerImageAdapter.BannerViewHolder>>>(
                R.id.banner
            )
        banner.adapter = imageAdapter
        banner.setBannerGalleryMZ(20);
        return view
    }

    private fun getAdHeaderView(adList: List<HomeAdResult>): View {
        val view = View.inflate(context, R.layout.advertising_space, null)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val imageView1 = view.findViewById<ImageView>(R.id.imageView1)
        val imageView2 = view.findViewById<ImageView>(R.id.imageView2)
        val imageView3 = view.findViewById<ImageView>(R.id.imageView3)
        val imageView4 = view.findViewById<ImageView>(R.id.imageView4)
        for (homeAdResult in adList) {
            when (adList.indexOf(homeAdResult)) {
                0 -> {
                    context?.let { GlideEngineLoging.createGlideEngine().loadDefaultImage(it,homeAdResult.image,imageView) }
                }
                1 -> {
                    context?.let { GlideEngineLoging.createGlideEngine().loadImageAsResId(it,homeAdResult.image,R.drawable.ad_map,imageView1) }
                }
                2 -> {
                    context?.let { GlideEngineLoging.createGlideEngine().loadDefaultImage(it,homeAdResult.image,imageView2) }
                }
                3 -> {
                    context?.let { GlideEngineLoging.createGlideEngine().loadDefaultImage(it,homeAdResult.image,imageView3) }
                }
                4 -> {
                context?.let { GlideEngineLoging.createGlideEngine().loadImageAsResRadiusId(it,homeAdResult.image,R.drawable.ad4_map,80,imageView4) }
                }
                else -> {
                    return view
                }
            }
        }
        return view
    }

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    inner class ProductListAdapter(layoutResId: Int, data: MutableList<HotResult.RowsBean>?) :
        BaseQuickAdapter<HotResult.RowsBean, BaseViewHolder>(layoutResId, data) {
        override fun convert(holder: BaseViewHolder, item: HotResult.RowsBean) {
            GlideEngineLoging.createGlideEngine().loadDefaultMapImage(context,item.image,holder.getView(R.id.commodity))
            SpanUtils.with(holder.getView(R.id.price)).append(YANG).setFontSize(10,true).append(item.price).setFontSize(17,true).create()
            holder.setText(R.id.name,item.name)
                .setText(R.id.payments,item.sales.toString()+peoplePay)
        }
    }

}