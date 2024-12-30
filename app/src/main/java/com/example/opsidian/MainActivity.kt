package com.example.opsidian

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // 첫 화면에 HomeFragment를 표시
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()

        // BottomNavigationView의 아이템 선택 리스너
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // HomeFragment 표시
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, HomeFragment())
                        .commit()
                    true
                }
                R.id.nav_cars -> {
                    // CarListFragment 표시
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, GalleryFragment())
                        .commit()
                    true
                }
                R.id.nav_gallerys -> {
                    // SettingsFragment 표시
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CarListFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
