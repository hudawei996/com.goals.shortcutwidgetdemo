package com.goals.shortcutwidgetdemo

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi

class App : Application() {

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    override fun onCreate() {
        super.onCreate()
        //动态配置快捷方式
        ShortcutHelper.setShortcut(this)
    }
}