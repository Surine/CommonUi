package cn.surine.ui

import Item
import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * Intro：
 *
 * @author sunliwei
 * @date 7/28/20 15:34
 */
class SelectorItem(
    val context: Context,
    private val normalSelect: IntArray,
    private val mutiSelect: Boolean,
    private val tag: String,
    val result: (array: IntArray) -> Unit
) {

    private val itemList = mutableListOf<Item>()
    val view: ViewGroup by lazy {
        LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
    }
    private val sp = context.getSharedPreferences("data", Context.MODE_PRIVATE)


    fun selectItem(s: String, subTitle: String = "") {
        val settingItem = Item(context, s, subTitle) {
            val cur = getCurSelectList().toIntArray()
            if (!mutiSelect && cur.isNotEmpty()) {
                clearAllSelected()
                selected()
            } else if (!mutiSelect) {
                //单选的时候，如果当前选中，再点当前，是不需要操作的
                if (!selected) {
                    selected()
                }
            } else {
                //多选
                selected()
            }
            getCurSelectList().toIntArray().apply {
                result(this)
                save(this)
            }

        }
        itemList.add(settingItem)
        view.addView(settingItem.view)
    }


    private fun clearAllSelected() {
        for (i in itemList) {
            i.clearSelected()
        }
    }


    private fun getCurSelectList(): MutableList<Int> {
        val res = mutableListOf<Int>()
        for (i in itemList.indices) {
            if (itemList[i].selected) {
                res.add(i)
            }
        }
        return res
    }

    fun init() {
        val spResult = sp.getString(tag, "")
        var curSelect = normalSelect
        if (spResult != null && spResult.isNotEmpty()) {
            curSelect = spResult.trim().split(" ").map { it.toInt() }.toIntArray()
        }
        for (i in curSelect) {
            if (itemList.size > i) {
                itemList[i].selected()
            }
        }
        result(curSelect)
        save(curSelect)
    }


    private fun save(ints: IntArray) {
        StringBuilder().apply {
            ints.forEach {
                this.append(it).append(" ")
            }
            sp.edit().putString(tag, this.toString().trim()).apply()
        }
    }
}