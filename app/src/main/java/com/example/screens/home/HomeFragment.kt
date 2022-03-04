package com.example.screens.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.base.BaseFragment
import com.example.base.LoadDataState
import com.example.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import com.example.utils.gone
import com.example.utils.logRenderTime
import com.example.utils.show
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: ActivityAdapter

    override fun inflateViewBinding(
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
        setupEvents()
        observeData()
    }

    private fun initViews() = with(viewBinding) {
        recyclerActivity.adapter = adapter
    }

    private fun initData() {
        viewModel.getActivity()
    }

    private fun setupEvents() = with(viewBinding) {

    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun observeData() = with(viewModel) {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityList.collect {
                    viewBinding.recyclerActivity.logRenderTime()
                    adapter.submitList(it)
                    Handler(Looper.getMainLooper()).postDelayed({
                        viewBinding.recyclerActivity.scrollToPosition(it.size - 1)
                    }, 200L)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loadDataState.collect {
                    when (it) {
                        LoadDataState.LOADING -> viewBinding.loadingBar.show()
                        LoadDataState.SUCCESS, LoadDataState.ERROR, LoadDataState.NONE -> viewBinding.loadingBar.gone()
                    }
                }
            }
        }
    }
}
