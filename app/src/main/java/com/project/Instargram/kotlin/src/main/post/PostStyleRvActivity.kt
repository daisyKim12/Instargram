package com.project.Instargram.kotlin.src.main.post

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityPostStyleRvBinding
import com.project.Instargram.kotlin.src.main.post.adapter.FilterAdapter
import com.project.Instargram.kotlin.src.main.post.adapter.MultiImageAdapter
import com.project.Instargram.kotlin.src.main.post.adapter.RetouchAdapter
import com.project.Instargram.kotlin.src.main.post.model.PageLists

class PostStyleRvActivity: BaseActivity<ActivityPostStyleRvBinding>(ActivityPostStyleRvBinding::inflate) {

    private lateinit var imagesToPost: ArrayList<String>
    private val KEY_MULTI = "multi_image"
    private val ISITSINGLE = "is_it_single_image"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imagesToPost = intent.getStringArrayListExtra(KEY_MULTI)!!

        loadStyleRecyclerView(0)
        loadImageRecyclerView()

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.tbNext.setOnClickListener {
            val intent = Intent(this, PostFinalActivity::class.java)
            intent.putExtra(ISITSINGLE, false)
            intent.putStringArrayListExtra(KEY_MULTI, imagesToPost)
            startActivity(intent)
        }

    }

    private fun loadStyleRecyclerView(id: Int) {
        binding.rvStyle.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvStyle.layoutManager = linearLayoutManager
        val adapter = FilterAdapter(this, PageLists.filterSlide, imagesToPost[0])
        adapter.notifyDataSetChanged()
        binding.rvStyle.adapter = adapter


    }

    private fun loadImageRecyclerView() {
        binding.rvResize.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvResize.layoutManager = linearLayoutManager

        val adapter = MultiImageAdapter(this, imagesToPost)
        adapter.notifyDataSetChanged()
        binding.rvResize.adapter = adapter
    }

}