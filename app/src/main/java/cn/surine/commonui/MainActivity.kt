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
                item("点击弹出"){
                    dialog(message = "这是一个对话框"){
                        positive {
                            Log.d("slw", "info: ");
                        }
                    }
                }
                switchItem("标题","打开","关闭",true,"1"){
                    view, isChecked ->
                }
            }
        }

    }
}