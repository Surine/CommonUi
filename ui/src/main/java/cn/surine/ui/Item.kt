import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import cn.surine.ui.R
import cn.surine.ui_lib.SettingItemConfig

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2020/6/25 18:38
 */
class Item(
    val context: Context,
    title: String,
    subTitle: String = "",
    onClick: Item.(view: View) -> Unit
) {
    val view by lazy {
        LayoutInflater.from(context)
            .inflate(R.layout.item_normal_setting, null)
    }
    var selected = false
    val vTitle: TextView
    val vSubTitle: TextView

    init {
        vTitle = view.findViewById(R.id.title)
        vSubTitle = view.findViewById(R.id.subtitle)
        vTitle.text = title
        if (subTitle.isNotEmpty()) {
            vSubTitle.visibility = View.VISIBLE
            vSubTitle.text = subTitle
        } else {
            vSubTitle.visibility = View.GONE
        }
        view.setOnClickListener {
            onClick(it)
        }
    }


    fun selected() {
        selected = !selected
        view.background = if (selected) {
            GradientDrawable().apply {
                color = ColorStateList.valueOf(SettingItemConfig.primaryColor)
                cornerRadius = 20F
            }
        } else {
            null
        }
        vTitle.setTextColor(if (selected) Color.WHITE else Color.BLACK)
        vSubTitle.setTextColor(if (selected) Color.WHITE else Color.parseColor("#8A000000"))
    }

    fun clearSelected() {
        selected = false
        view.background = null
        vTitle.setTextColor(Color.BLACK)
        vSubTitle.setTextColor(Color.parseColor("#8A000000"))
    }
}