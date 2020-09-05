package cn.surine.sunny_ui.setting

import Header
import Item
import SliderItem
import SwitchItem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import cn.surine.ui.SelectorItem
import com.google.android.material.slider.Slider

/**
 * Intro：
 * 组
 * @author sunliwei
 * @date 2020/6/25 18:26
 */
class Group(val context: Context, title: String = "") {
    val root by lazy {
        LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
    }

    init {
        if (title.isNotEmpty()) {
            root.addView(Header(context, title).titleView)
        }
    }

    /**
     * 构建一个标准设置项
     * */
    fun item(title: String, subTitle: String = "", block: Item.(view: View) -> Unit): Item {
        val item = Item(context, title, subTitle, block)
        root.addView(item.view)
        return item
    }


    /**
     * 构建一个带开关的设置项
     * */
    fun switchItem(
        title: String,
        openSubTitle: String = "",
        closeSubTitle: String = "",
        initValue: Boolean = false,
        tag: String = "",
        block: SwitchItem.(view: View, isChecked: Boolean) -> Unit
    ): SwitchItem {
        val item = SwitchItem(context, title, openSubTitle, closeSubTitle, initValue, tag, block)
        root.addView(item.view)
        return item
    }


    /**
     * 构建一个带滑动杆设置项
     * */
    fun sliderItem(
        title: String, subTitle: String = "",
        valueFrom: Float = 0F,
        valueTo: Float = 100F,
        step: Float = 1F,
        initValue: Float = 0F,
        tag: String = "",
        block: SliderItem.(view: Slider, value: Float) -> Unit
    ): SliderItem {
        val item =
            SliderItem(context, title, subTitle, valueFrom, valueTo, step, initValue, tag, block)
        root.addView(item.view)
        return item
    }


    /**
     * 添加自定义布局
     * */
    fun view(layoutId: Int, block: (view: View) -> Unit): View {
        val view = LayoutInflater.from(context).inflate(layoutId, null)
        root.addView(view)
        block(view)
        return view
    }

    fun selector(
        normalSelect: IntArray,
        mutiSelect: Boolean = false,
        tag: String,
        element: SelectorItem.() -> Unit,
        result: (array: IntArray) -> Unit
    ): SelectorItem {
        val item = SelectorItem(context, normalSelect, mutiSelect, tag, result)
        item.element()
        item.init()
        root.addView(item.view)
        return item
    }
}