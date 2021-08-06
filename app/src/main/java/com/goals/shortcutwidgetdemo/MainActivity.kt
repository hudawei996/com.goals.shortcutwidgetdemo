package com.goals.shortcutwidgetdemo

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.goals.shortcutwidgetdemo.widget.BroadcastUtil
import com.goals.shortcutwidgetdemo.widget.OpenDoorNewWidget

class MainActivity : AppCompatActivity() {
    private val TAG :String = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)

        //https://blog.csdn.net/weixin_42605341/article/details/82656103
        //如果此时的广播是发给MyAppwidgetProvider自己的，则需要添加：
        //intent_count.setComponent(new ComponentName(context,MyAppWidgetProvider.class));//必须写
        //如果此时的广播是发给其他的service或者activity，则不要添加setComponent，调试发现加了广播收不到。
        btn.setOnClickListener {
            sendOpenDoorBroadcast(BroadcastUtil.TagWidget.LOGOUT)
        }
    }

    private fun sendOpenDoorBroadcast(it: String?) {
        val intent = Intent(BroadcastUtil.ACTION_WIDGET)
        var tag =
            (if (it == null) BroadcastUtil.TagWidget.LOGOUT else BroadcastUtil.TagWidget.LOGIN)
        intent.putExtra(TAG, tag)
        intent.component = ComponentName(this, OpenDoorNewWidget::class.java)
        sendBroadcast(intent)
    }
}