package com.example.prepotency.widght.popup

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.decorview.utils.Utils
import com.example.prepotency.R
import razerdp.basepopup.BasePopupWindow

class VersionUpdatingPopUp(private val context: Context) : BasePopupWindow(context),
    View.OnClickListener {

    private var image: ImageView? = null
    private var title: TextView? = null
    private var content: TextView? = null
    private var upgradeBtn: TextView? = null

    init {
        popupGravity = Gravity.CENTER
        findbyid()
    }

    private fun findbyid() {
        image = findViewById(R.id.image)
        title = findViewById(R.id.title)
        content = findViewById(R.id.content)
        upgradeBtn = findViewById(R.id.upgradeBtn)
        upgradeBtn!!.setOnClickListener(this)
    }

    fun setContent(content: String?) {
        this.content!!.text = content
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.upgradeBtn ->
                Utils.instance?.goMarket(context)
            else -> {
            }
        }
    }

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.popup_version_updating)
    }


}
