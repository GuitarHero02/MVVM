package com.tistory.deque.mvvm.adapter

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.tistory.deque.mvvm.repository.Bookmark
import com.tistory.deque.mvvm.viewmodel.BookMarkViewModel

/**
 * @author Leopold
 */

@BindingAdapter(value = ["bookmarks", "viewModel"])
fun setBookmarks(view: RecyclerView, items: PagedList<Bookmark>?, vm: BookMarkViewModel) {
    view.adapter?.run {
        if (this is BookmarkAdapter) this.submitList(items)
    } ?: run {
        BookmarkAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}