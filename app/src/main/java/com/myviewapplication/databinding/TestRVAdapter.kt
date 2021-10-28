package com.myviewapplication.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Jeff on 2021/9/22
 */
class TestRVAdapter : RecyclerView.Adapter<TestRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(position.toString())
    }

    override fun getItemCount(): Int {
        return 16
    }

    class ViewHolder(private val viewDataBinding: ItemTestBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun binding(text: String) {
            viewDataBinding.text = text
            viewDataBinding.executePendingBindings()
        }
    }
}