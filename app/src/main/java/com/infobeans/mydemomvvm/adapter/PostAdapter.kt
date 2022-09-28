package com.infobeans.mydemomvvm.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.infobeans.mydemomvvm.R
import com.infobeans.mydemomvvm.databinding.EachRowBinding
import com.infobeans.mydemomvvm.model.Post


class PostAdapter(private var postList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    private lateinit var binding: EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding)

        /*val inflator: LayoutInflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate(inflator, R.layout.each_row , parent, false)*/

//        val binder = DataBindingUtil.inflate(
//        LayoutInflater.from(parent.context),
//        R.layout.each_row,
//        parent,
//        false
//        )

//        return PostViewHolder(DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context),
//            R.layout.each_row,
//            parent,
//            false
//        ))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        Log.d("Click recycler", postList[position].body.toString())
        binding.apply {
            post = postList[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        Log.d("Click list size","${postList.size}")
        return postList.size
    }

    class PostViewHolder(val binding: EachRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


    fun setData( postList: List<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }

}