package com.tistory.deque.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.deque.mvvm.base.BindingViewHolder
import com.tistory.deque.mvvm.databinding.RvItemCatListBinding
import com.tistory.deque.mvvm.model.Cat

class CatListAdapter : ListAdapter<Cat, CatListAdapter.ViewHolder>(CatListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemCatListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(cat)

    }

    class ViewHolder(val binding: RvItemCatListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mCat: Cat) {
            binding.apply {
                cat = mCat
                executePendingBindings()
            }
        }
    }
}

private class CatListDiffCallback : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return true
    }

}
