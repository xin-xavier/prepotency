package com.example.decorview.utils

object Utils {
    @Volatile
    private var singleton: Utils? = null
    val instance: Utils?
        get() {
            if (singleton == null) {
                synchronized(Utils::class.java) {
                    if (singleton == null) {
                        singleton = Utils
                    }
                }
            }
            return singleton
        }
}