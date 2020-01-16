package com.tistory.deque.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.base.BindingViewHolder
import com.tistory.deque.mvvm.databinding.ItemBookmarkBinding
import com.tistory.deque.mvvm.repository.Bookmark
import com.tistory.deque.mvvm.viewmodel.BookMarkViewModel

/**
 * @author Leopold
 */
class BookmarkAdapter(val vm: BookMarkViewModel) :
    PagedListAdapter<Bookmark, BookmarkAdapter.BookmarkViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bookmark,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        getItem(position)?.run {
            holder.binding.item = this
            holder.binding.vm = vm
        }
    }

    class BookmarkViewHolder(view: View) : BindingViewHolder<ItemBookmarkBinding>(view)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Bookmark>() {
            override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark) = oldItem == newItem
        }
    }
}