package com.example.prepotency.widght.popup

import android.content.Context
import android.view.View
import com.example.prepotency.R
import razerdp.basepopup.BasePopupWindow

class UserAgreementPopup (private val context: Context) : BasePopupWindow(context) {
    override fun onCreateContentView(): View {
        return createPopupById(R.layout.dialog_user_agreement)
    }

    init {
        find
    }


}