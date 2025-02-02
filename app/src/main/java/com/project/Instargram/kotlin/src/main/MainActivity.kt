package com.project.Instargram.kotlin.src.main

import com.project.Instargram.kotlin.R
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityMainBinding
import com.project.Instargram.kotlin.src.main.home.HomeFragment
import com.project.Instargram.kotlin.src.main.page.MyPageFragment
import com.project.Instargram.kotlin.src.main.post.PostImageActivity
import com.project.Instargram.kotlin.src.main.reels.ReelsFragment
import com.project.Instargram.kotlin.src.main.search.SearchFragment


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val KEY_GET = "image_path"
    private var imageUri = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        imageUri = getStringValue(KEY_GET)!!
        loadImageInBottomNav(imageUri!!)
    }

    override fun onResume() {
        super.onResume()

        binding.mainBtmNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_home -> {
                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
                    loadHomeBottomNav(imageUri!!)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                }
                R.id.menu_main_search -> {
                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
                    loadSearchBottomNav(imageUri!!)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                }
                R.id.menu_main_post -> {
                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
                    loadOtherBottomNav(imageUri!!)
                    startActivity(Intent(this, PostImageActivity::class.java))

//                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, PostFragment())
//                        .commitAllowingStateLoss()
                }
                R.id.menu_main_reels -> {
                    binding.mainBtmNav.setBackgroundColor(Color.BLACK)
                    loadReelsBottomNav(imageUri!!)

//                    val menu: Menu = binding.mainBtmNav.getMenu()
//                    menu.findItem(R.id.menu_main_home).setIcon(R.drawable.ic_nav_home_white)


                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ReelsFragment())
                        .commitAllowingStateLoss()
                }
                R.id.menu_main_mypage -> {
                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
                    loadOtherBottomNav(imageUri!!)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyPageFragment())
                        .commitAllowingStateLoss()
                }
            }
            true
        }
    }

    fun loadImageInBottomNav(uri: String) {
        binding.mainBtmNav.itemIconTintList = null

        Glide.with(this@MainActivity)
            .asBitmap()
            .load(uri)
            .apply(RequestOptions.circleCropTransform()).into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                ) {
                    val drawable: Drawable = BitmapDrawable(resources, resource)
                    binding.mainBtmNav.menu.findItem(R.id.menu_main_mypage)
                        .setIcon(drawable)                }
            })
    }

    fun loadReelsBottomNav(uri: String) {
        binding.mainBtmNav.menu.findItem(R.id.menu_main_home).setIcon(R.drawable.ic_nav_home_white)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_search).setIcon(R.drawable.ic_nav_search_white)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_post).setIcon(R.drawable.ic_nav_post_white)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_reels).setIcon(R.drawable.ic_nav_reels_white)
        loadImageInBottomNav(uri)
    }

    fun loadHomeBottomNav(uri: String) {
        binding.mainBtmNav.menu.findItem(R.id.menu_main_home).setIcon(R.drawable.ic_nav_home_fill)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_search).setIcon(R.drawable.ic_nav_search)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_post).setIcon(R.drawable.ic_nav_post)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_reels).setIcon(R.drawable.ic_nav_reels)
        loadImageInBottomNav(uri)
    }

    fun loadSearchBottomNav(uri: String) {
        binding.mainBtmNav.menu.findItem(R.id.menu_main_home).setIcon(R.drawable.ic_nav_home)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_search).setIcon(R.drawable.ic_nav_search_fill)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_post).setIcon(R.drawable.ic_nav_post)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_reels).setIcon(R.drawable.ic_nav_reels)
        loadImageInBottomNav(uri)
    }

    fun loadOtherBottomNav(uri: String) {
        binding.mainBtmNav.menu.findItem(R.id.menu_main_home).setIcon(R.drawable.ic_nav_home)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_search).setIcon(R.drawable.ic_nav_search)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_post).setIcon(R.drawable.ic_nav_post)
        binding.mainBtmNav.menu.findItem(R.id.menu_main_reels).setIcon(R.drawable.ic_nav_reels)
        loadImageInBottomNav(uri)
    }

}
