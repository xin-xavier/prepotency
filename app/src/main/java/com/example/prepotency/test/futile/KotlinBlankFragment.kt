package com.example.prepotency.test.futile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.prepotency.R
import com.example.prepotency.adapter.pager.BannerImageAdapter
import com.example.prepotency.bean.result.HotResult
import com.example.prepotency.bean.result.SlideShowResult
import com.example.prepotency.main.home.container.HomeContainerFragment
import com.example.prepotency.main.home.container.HomeContract
import com.example.prepotency.main.home.container.HomePresenter
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.fragment_kotlin_blank.*


class KotlinBlankFragment : HomeContainerFragment() {

    private val rowsList = ArrayList<HotResult.RowsBean>()
    private val adapter = ProductListAdapter(R.layout.test_item, rowsList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kotlin_blank, container, false)
    }

    override fun init() {
        super.init()
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter
    }

    override fun onLazyLoad() {}

    override fun showHot(hotResult: HotResult) {
        val rows = hotResult.rows
        rowsList.clear()
        rowsList.addAll(rows)
        adapter.notifyDataSetChanged()
    }

    override fun showSlideShow(slideList: List<SlideShowResult>) {
        adapter.addHeaderView(getHeaderView(slideList))
        adapter.notifyDataSetChanged()
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

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    override fun onError(error: String) {
        ToastUtils.showShort(error)
    }

    inner class ProductListAdapter(layoutResId: Int, data: MutableList<HotResult.RowsBean>?) :
        BaseQuickAdapter<HotResult.RowsBean, BaseViewHolder>(layoutResId, data) {
        override fun convert(holder: BaseViewHolder, item: HotResult.RowsBean) {
            holder.setText(R.id.textView, item.name)
        }
    }

}