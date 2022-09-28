package com.infobeans.mydemomvvm.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.infobeans.mydemomvvm.R
import com.infobeans.mydemomvvm.adapter.PostAdapter
import com.infobeans.mydemomvvm.viewModel.MainViewModel
import com.infobeans.mydemomvvm.databinding.ActivityMainBinding
import com.infobeans.mydemomvvm.util.ApiState
/*import dagger.hilt.android.AndroidEntryPoint*/
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.text.Typography.dagger


/*@AndroidEntryPoint*/
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    /*private val mainViewModel: MainViewModel by viewModels()*/
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewmodel = this@MainActivity.mainViewModel
            lifecycleOwner = this@MainActivity
        }


        initRecyclerview()
        /*mainViewModel.getPost()*/

        lifecycleScope.launchWhenStarted {
            mainViewModel.postList.observe(this@MainActivity, Observer { it->
                when (it) {
                    is ApiState.Loading -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@MainActivity,"${it.msg}",Toast.LENGTH_LONG).show()
                    }
                    is ApiState.Success -> {
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        postAdapter.setData(it.data)
                    }
                    is ApiState.Empty -> {
                    }
                }
            })


            /*mainViewModel._postStateFlow.collect { it ->
                when (it) {
                    is ApiState.Loading -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        postAdapter.setData(it.data)
                        postAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty -> {
                    }
                }
            }*/
        }

    }


    private fun initRecyclerview() {
        postAdapter = PostAdapter(ArrayList())
        Log.d("Click","initRecyclerView")
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }

        Log.d("Click","initRecyclerView end")
    }
}