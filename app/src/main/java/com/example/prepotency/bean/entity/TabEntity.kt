package com.example.prepotency.bean.entity

import com.flyco.tablayout.listener.CustomTabEntity

class TabEntity : CustomTabEntity {
    var title: String? = null
    var selectedIcon = 0
    var unSelectedIcon = 0

    constructor() {}
    constructor(title: String?, selectedIcon: Int, unSelectedIcon: Int) {
        this.title = title
        this.selectedIcon = selectedIcon
        this.unSelectedIcon = unSelectedIcon
    }

    override fun getTabTitle(): String? {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}