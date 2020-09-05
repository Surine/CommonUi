package cn.surine.ui_lib

import android.content.Context
import android.graphics.Color

/**
 * Intro：
 * 配置项，通过配置一些列参数，协调UI界面
 * @author sunliwei
 * @date 2020/6/24 21:40
 */
object SettingItemConfig {
    lateinit var context: Context
    var primaryColor: Int = Color.BLUE
    var lightGray = Color.parseColor("#D5D5D5")
    fun init(context: Context, color: Int) {
        this.context = context
        this.primaryColor = color
    }
}