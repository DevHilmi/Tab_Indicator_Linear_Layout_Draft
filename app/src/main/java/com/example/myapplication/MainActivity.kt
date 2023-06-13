package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewpager2 = findViewById<ViewPager2>(R.id.view_pager2)
        val tabCustom = findViewById<TabIndicatorCustom>(R.id.linera)
        val list = listOf("miaw","miaw","guguk","miaw","miaw","guguk","miaw","miaw","guguk","miaw","miaw","guguk")
        val adapter = ViewPager2Adapter(list)
        viewpager2.adapter = adapter
        adapter.notifyItemInserted(0)
        list.forEach { _ ->
            tabCustom.addIndicator()
        }
        viewpager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                tabCustom.setSelected(position)
            }
        })
    }
}