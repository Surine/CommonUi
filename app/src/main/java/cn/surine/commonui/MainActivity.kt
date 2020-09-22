package cn.surine.commonui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.surine.ui_lib.dialog
import cn.surine.ui_lib.setting
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setting(root){
            group {
                item("普通Item演示","点击打开对话框"){
                    dialog { 
                        
                    }
                }
                switchItem("开关演示","当前已打开","当前已关闭",true,"tag"){
                    view, isChecked ->
//                    if(isChecked) Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                }
                sliderItem("标题","副标题"){
                    view, value ->
                }
                selector(normalSelect = intArrayOf(0),mutiSelect = false,tag = "selector",element = {
                    selectItem("标题","副标题")
                    selectItem("标题","副标题")
                    selectItem("标题","副标题")
                }){
                    //选择结果
                }
                view(R.layout.activity_main){
                    view ->
                }
            }
        }

    }
}