package com.tistory.deque.mvvm.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.deque.mvvm.model.Repository
import com.tistory.deque.mvvm.viewmodel.SearchViewModel

/**
 * @author Leopold
 */
@BindingAdapter(value = ["repositories", "viewModel"])
fun setRepositories(view: RecyclerView, items: List<Repository>, vm: SearchViewModel) {
    view.adapter?.let {
        if (it is RepositoryAdapter) {
            it.items = items
            it.notifyDataSetChanged()
        }
    } ?: run {
        RepositoryAdapter(items, vm).apply { view.adapter = this }
    }
}