package com.example.prepotency.app.splash

import android.os.Bundle
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.decorview.utils.Utils
import com.example.prepotency.MainActivity
import com.example.prepotency.R
import com.example.prepotency.app.base.viewstratum.activity.SimpleActivty
import com.example.prepotency.utils.GlideEngineLoging
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : SimpleActivty() {

    private val guides = mutableListOf<Int>(
        R.drawable.guide,
        R.drawable.guide1,
        R.drawable.guide2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
    }

    override fun init() {
        Utils.instance?.showUserAgreementDialog(context,supportFragmentManager)

        viewPager.adapter = GuidePagerAdapter(R.layout.guide_item, guides)
    }

    inner class GuidePagerAdapter(layoutResId: Int, data: MutableList<Int>) :
        BaseQuickAdapter<Int, BaseViewHolder>(layoutResId, data) {
        override fun convert(holder: BaseViewHolder, item: Int) {
            GlideEngineLoging.createGlideEngine().loadAsGifImage(context, item, holder.getView(
                R.id.imageView
            ))
            if (holder.layoutPosition == guides.size - 1) {
                holder.setVisible(R.id.tryItNow,true)
                holder.getView<TextView>(R.id.tryItNow).setOnClickListener {
                    intent(MainActivity::class.java)
                    finish()
                }
            }else{
                holder.setGone(R.id.tryItNow,true)
            }
        }
    }

}
