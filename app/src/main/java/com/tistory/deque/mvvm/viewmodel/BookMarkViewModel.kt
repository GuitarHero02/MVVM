package com.tistory.deque.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tistory.deque.mvvm.base.BaseKotlinViewModel
import com.tistory.deque.mvvm.repository.Bookmark
import com.tistory.deque.mvvm.repository.dao.BookmarkDao
import com.tistory.deque.mvvm.util.ioThread

class BookMarkViewModel(private val dao: BookmarkDao) : BaseKotlinViewModel() {
    val items: LiveData<PagedList<Bookmark>> = LivePagedListBuilder(dao.findAll(),  /* page size */ 10).build()

    fun delete(bookmark: Bookmark) = ioThread { dao.delete(bookmark) }
}