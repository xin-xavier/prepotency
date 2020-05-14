package com.example.prepotency.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ResourceUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.prepotency.R
import com.example.prepotency.app.base.viewstratum.activity.SimpleActivty
import com.example.prepotency.bean.BaseData
import com.example.prepotency.bean.Entity
import kotlinx.android.synthetic.main.activity_test_recyclerview_header.*

class TestRecyclerviewHeaderActivity : SimpleActivty() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recyclerview_header)
    }

    override fun initStatusBar() {

    }

    override fun init() {
        val arrayList = ArrayList<Entity>()
        arrayList.add(Entity("fsaf"))
        arrayList.add(Entity("htrh"))
        arrayList.add(Entity("ergbh"))
        val adapter = RecyclerviewHeaderAdpater(R.layout.test_item, arrayList)
        recyclerView.adapter=adapter
        // 指定添加位置
        adapter.addHeaderView(getHeaderView(1), 1);
        // 指定添加位置
        adapter.addHeaderView(getHeaderView(2), 2);
    }

    private fun getHeaderView(i: Int): View {
        val view = View.inflate(context, R.layout.test_item, null)
        val testView= view.findViewById<TextView>(R.id.textView);
        when (i) {
            1->{
                testView.setText("1...")
                view.setBackgroundColor(ColorUtils.getColor(R.color.red))
            }
            else->{
                testView.setText("2...")
                view.setBackgroundColor(ColorUtils.getColor(R.color.blue))
            }
        }
        return view
    }


    inner class RecyclerviewHeaderAdpater(layoutResId: Int, data: MutableList<Entity>?) :
        BaseQuickAdapter<Entity, BaseViewHolder>(layoutResId, data) {
        override fun convert(holder: BaseViewHolder, item: Entity) {
            holder.setText(R.id.textView,item.text)
        }
    }
}
