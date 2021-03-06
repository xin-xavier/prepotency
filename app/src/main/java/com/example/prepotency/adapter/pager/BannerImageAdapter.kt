package com.example.prepotency.adapter.pager

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.bumptech.glide.Glide
import com.example.prepotency.R
import com.example.prepotency.app.api.ConstantPool
import com.example.prepotency.bean.result.SlideShowResult
import com.example.prepotency.utils.GlideEngineLoging
import com.youth.banner.adapter.BannerAdapter

class BannerImageAdapter(slideList: List<SlideShowResult>) :
    BannerAdapter<SlideShowResult, BannerImageAdapter.BannerViewHolder>(
        slideList
    ) {

    private var context : Context?=null

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        context=parent!!.context
        val imageView = ImageView(context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        val layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layoutParams.setMargins(
            SizeUtils.dp2px(4.toFloat()), 0, SizeUtils.dp2px(
                4.toFloat()
            ), 0
        )
        imageView.layoutParams = layoutParams
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: SlideShowResult,
        position: Int,
        size: Int
    ) {
        GlideEngineLoging.createGlideEngine().loadImageAsResId(context,data.image,R.drawable.banner_map,holder.imageView)
        //GlideEngineLoging.createGlideEngine().loadAsGifImage(context!!,data.image,holder.imageView)
    }

    inner class BannerViewHolder(@NonNull view: ImageView) :
        RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view
    }

}