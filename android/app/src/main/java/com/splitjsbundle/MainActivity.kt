package com.splitjsbundle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        findViewById<Button>(R.id.singleBundleBtn).setOnClickListener {
            startActivity(Intent(this, SingleBundleActivity::class.java))
        }
        findViewById<Button>(R.id.multiBundleBtn).setOnClickListener {
            startActivity(Intent(this, MultiBundleActivity::class.java))
        }
    }
}