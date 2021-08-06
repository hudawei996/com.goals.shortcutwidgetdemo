package com.goals.shortcutwidgetdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class NewMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_main)

        var routeStr = intent.extras?.get("route") as String
        Toast.makeText(this,routeStr,Toast.LENGTH_LONG).show()
    }
}