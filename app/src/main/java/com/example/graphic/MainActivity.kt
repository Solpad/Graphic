package com.example.graphic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mydraw = MyDraw(this)
        setContentView(R.layout.activity_main)
    }
}