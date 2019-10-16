package com.rami.koroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DemoApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)
    }
}
