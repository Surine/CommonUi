import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.StateSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Switch
import android.widget.TextView
import cn.surine.ui.R
import cn.surine.ui_lib.SettingItemConfig


/**
 * Intro：
 *
 * @author sunliwei
 * @date 2020/6/25 19:27
 */
class SwitchItem(
        val context: Context,
        title: String,
        openSubTitle: String = "",
        closeSubTitle: String = "",
        initValue: Boolean = false,
        tag: String = "",
        onClick: SwitchItem.(view: Switch, isChecked: Boolean) -> Unit
) {
    val view: View by lazy {
        LayoutInflater.from(context)
                .inflate(R.layout.item_switch_setting, null)
    }
    private val sp = context.getSharedPreferences("data", Context.MODE_PRIVATE)

    private val vTitle: TextView
    private val vSubTitle: TextView
    private val vSwitch: Switch

    init {
        vTitle = view.findViewById(R.id.title)
        vSubTitle = view.findViewById(R.id.subtitle)
        vSwitch = view.findViewById(R.id.switchs)
        vTitle.text = title
        val checked = GradientDrawable().apply {
            setColor(SettingItemConfig.primaryColor)
            cornerRadius = 180F
        }
        val unChecked =  GradientDrawable().apply {
            setColor(SettingItemConfig.lightGray)
            cornerRadius = 180F
        }
        vSwitch.trackDrawable = StateListDrawable().apply {
            addState(intArrayOf(-android.R.attr.state_checked),unChecked)
            addState(intArrayOf(android.R.attr.state_checked),checked)
        }
        vSwitch.isChecked = sp.getBoolean(tag, initValue)
        if (vSwitch.isChecked) {
            //按钮选中
            if (openSubTitle.isNotEmpty()) {
                vSubTitle.visibility = View.VISIBLE
                vSubTitle.text = openSubTitle
            } else {
                vSubTitle.visibility = View.GONE
            }
        } else {
            //按钮没选中
            if (closeSubTitle.isNotEmpty()) {
                vSubTitle.visibility = View.VISIBLE
                vSubTitle.text = closeSubTitle
            } else {
                vSubTitle.visibility = View.GONE
            }
        }

        view.setOnClickListener {
            vSwitch.isChecked = !vSwitch.isChecked
        }
        vSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (tag.isNotEmpty()) {
                sp.edit().putBoolean(tag, isChecked).apply()
            }
            //对初值修改
            vSubTitle.text = if (isChecked) openSubTitle else closeSubTitle
            onClick(vSwitch, isChecked)
        }
    }
}