package com.example.chemistrybook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.chemistrybook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val totalPages = 193

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupPageIndicator()
    }

    private fun setupViewPager() {
        val adapter = BookPageAdapter(this, totalPages)
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.viewPager.setPageTransformer { page, position ->
            page.alpha = 1 - kotlin.math.abs(position)
        }
    }

    private fun setupPageIndicator() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val pageNum = position + 1
                binding.pageIndicator.text = getString(R.string.page_number, pageNum, totalPages)
            }
        })

        binding.pageIndicator.text = getString(R.string.page_number, 1, totalPages)
    }
}
