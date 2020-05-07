package com.example.prepotency.app.api

import com.example.prepotency.R

interface ConstantPool {
    companion object {
        const val APP_NAME = "SkilTree";

        const val Http_TAG = "OKHttpClient: "

        const val ARG_PARAM_VIEW = "floatingActionButton"
        const val ARG_PARAM_ID = "pageItem"
        const val ARG_PARAM_CLASS = "pageClass"

        const val SOCKET_CLIENT_TAG = "WebSocketClient"
        const val PONG = "PONG"
        const val PING = "PING"
        const val SOCKET_BROADCAST_ACTION = "com.example.chatexample.MINGW_IM"
        const val MESSAGE = "message"
        const val PUSH_STATUS = "PushStatus"

        const val GRAY_SERVICE_ID = 9501
        const val SETTING_FOR_RESULTCODE = 100

        const val IDENTIFIER_NAME = "status_bar_height"
        const val DEF_TYPE = "dimen"
        const val ANDROID = "android"

        const val HOME = "首页"
        const val NEWS = "消息"
        const val SHOPPING_CART = "购物车"
        const val MINE = "我的"

        val TABS: Array<String> = arrayOf(HOME, NEWS, SHOPPING_CART, MINE)
        //val TABS: Array<String> = arrayOf("", "", "", "")
        val ICON_SELECT_IDS: Array<Int> = arrayOf(
            R.mipmap.tab_home_select,
            R.mipmap.tab_news_select,
            R.mipmap.tab_shopping_select,
            R.mipmap.tab_mine_select
        )
        val ICON_UNSELECT_IDS: Array<Int> = arrayOf(
            R.mipmap.tab_home_unselect,
            R.mipmap.tab_news_unselect,
            R.mipmap.tab_shopping_unselect,
            R.mipmap.tab_mine_unselect
        )

        const val C_1 = "1"
        const val C_2 = "2"
        const val C_3 = "3"

        const val M_1 = -1
        const val _0 = 0
        const val _1 = 1
        const val _2 = 2
        const val _3 = 3
        const val _4 = 4
        const val _5 = 5
        const val _6 = 6
        const val _7 = 7
        const val _8 = 8
        const val _9 = 9
        const val _10 = 10
        const val _11 = 11
        const val _12 = 12
        const val _13 = 13
        const val _14 = 14
        const val _15 = 15
        const val _16 = 16
        const val _17 = 17
        const val _18 = 18
        const val _19 = 19
        const val _20 = 20
        const val _21 = 21
        const val _22 = 22
        const val _23 = 23
        const val _24 = 24
        const val _40 = 40

        const val NOTIFICATION_SAMPLE = 0
        const val NOTIFICATION_ACTION = 1
        const val NOTIFICATION_REMOTE_INPUT = 2
        const val NOTIFICATION_BIG_PICTURE_STYLE = 3
        const val NOTIFICATION_BIG_TEXT_STYLE = 4
        const val NOTIFICATION_INBOX_STYLE = 5
        const val NOTIFICATION_MEDIA_STYLE = 6
        const val NOTIFICATION_MESSAGING_STYLE = 7
        const val NOTIFICATION_PROGRESS = 8
        const val NOTIFICATION_CUSTOM_HEADS_UP = 9
        const val NOTIFICATION_CUSTOM = 10

        const val ACTION_SIMPLE = "com.android.peter.notificationdemo.ACTION_SIMPLE"
        const val ACTION_ACTION = "com.android.peter.notificationdemo.ACTION_ACTION"
        const val ACTION_REMOTE_INPUT =
            "com.android.peter.notificationdemo.ACTION_REMOTE_INPUT"
        const val ACTION_BIG_PICTURE_STYLE =
            "com.android.peter.notificationdemo.ACTION_BIG_PICTURE_STYLE"
        const val ACTION_BIG_TEXT_STYLE =
            "com.android.peter.notificationdemo.ACTION_BIG_TEXT_STYLE"
        const val ACTION_INBOX_STYLE =
            "com.android.peter.notificationdemo.ACTION_INBOX_STYLE"
        const val ACTION_MEDIA_STYLE =
            "com.android.peter.notificationdemo.ACTION_MEDIA_STYLE"
        const val ACTION_MESSAGING_STYLE =
            "com.android.peter.notificationdemo.ACTION_MESSAGING_STYLE"
        const val ACTION_PROGRESS = "com.android.peter.notificationdemo.ACTION_PROGRESS"
        const val ACTION_CUSTOM_HEADS_UP_VIEW =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_HEADS_UP_VIEW"
        const val ACTION_CUSTOM_VIEW =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW"
        const val ACTION_CUSTOM_VIEW_OPTIONS_LOVE =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_LOVE"
        const val ACTION_CUSTOM_VIEW_OPTIONS_PRE =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_PRE"
        const val ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE"
        const val ACTION_CUSTOM_VIEW_OPTIONS_NEXT =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_NEXT"
        const val ACTION_CUSTOM_VIEW_OPTIONS_LYRICS =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_LYRICS"
        const val ACTION_CUSTOM_VIEW_OPTIONS_CANCEL =
            "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_CANCEL"

        const val ACTION_YES = "com.android.peter.notificationdemo.ACTION_YES"
        const val ACTION_NO = "com.android.peter.notificationdemo.ACTION_NO"
        const val ACTION_DELETE = "com.android.peter.notificationdemo.ACTION_DELETE"
        const val ACTION_REPLY = "com.android.peter.notificationdemo.ACTION_REPLY"
        const val REMOTE_INPUT_RESULT_KEY = "remote_input_content"

        const val EXTRA_OPTIONS = "options"
        const val MEDIA_STYLE_ACTION_DELETE = "action_delete"
        const val MEDIA_STYLE_ACTION_PLAY = "action_play"
        const val MEDIA_STYLE_ACTION_PAUSE = "action_pause"
        const val MEDIA_STYLE_ACTION_NEXT = "action_next"
        const val ACTION_ANSWER = "action_answer"
        const val ACTION_REJECT = "action_reject"

        val HTML = "HTML"
        val CSS = "CSS"
        val JAVASCRIPT = "JAVASCRIPT"
        val JQUERY = "JQUERY"
        val BOOTSTRAP = "BOOTSTRAP"
        val PYTHON3 = "PYTHON3"
        val PYTHON2 = "PYTHON2"
        val JAVA = "JAVA"
        val C = "C"
        val C_C = "C++"
        val C_CC = "C#"
        val SQL = "SQL"
        val MYSQL = "MYSQL"
        val PHP = "PHP"

        val RUNOOB: Array<String> = arrayOf(
            HTML, CSS, JAVASCRIPT, JQUERY, BOOTSTRAP,
            PYTHON3, PYTHON2,
            JAVA,
            C, C_C, C_CC,
            SQL, MYSQL,
            PHP
        )
    }
}