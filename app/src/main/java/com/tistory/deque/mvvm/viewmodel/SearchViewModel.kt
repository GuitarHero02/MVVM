package com.tistory.deque.mvvm.viewmodel

import com.tistory.deque.mvvm.base.BaseKotlinViewModel
import com.tistory.deque.mvvm.model.Repository
import com.tistory.deque.mvvm.model.service.SearchAPI
import com.tistory.deque.mvvm.repository.Bookmark
import com.tistory.deque.mvvm.repository.dao.BookmarkDao
import com.tistory.deque.mvvm.util.NotNullMutableLiveData
import com.tistory.deque.mvvm.util.ioThread
import com.tistory.deque.mvvm.util.with

/**
 * @author Leopold
 */
class SearchViewModel(private val api: SearchAPI, private val dao: BookmarkDao) : BaseKotlinViewModel() {
    private var query: String = ""
        get() = if (field.isEmpty()) "MVVM" else field

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _items: NotNullMutableLiveData<List<Repository>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Repository>>
        get() = _items

    fun doSearch() {
        val params = mutableMapOf<String, String>().apply {
            this["q"] = query
            this["sort"] = "stars"
        }

        addDisposable(api.search(params).with()
            .doOnSubscribe { _refreshing.value = true }
            .doOnSuccess { _refreshing.value = false }
            .doOnError { _refreshing.value = false }
            .subscribe({
                _items.value = it.repositories
            }, {
                // handle errors
            }))
    }

    fun onQueryChange(query: CharSequence) {
        this.query = query.toString()
    }

    fun saveToBookmark(repository: Repository) = ioThread { dao.insert(Bookmark.to(repository)) }
}