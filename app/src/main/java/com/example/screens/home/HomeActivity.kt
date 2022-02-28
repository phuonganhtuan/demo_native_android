package com.example.screens.home

import android.os.Bundle
import com.example.base.BaseActivity
import com.example.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun inflateViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun onActivityReady(savedInstanceState: Bundle?) {
    }

    override fun onActivityReady() {

    }
}
