package com.tistory.deque.mvvm.viewmodel

import com.tistory.deque.mvvm.base.BaseKotlinViewModel
import com.tistory.deque.mvvm.repository.dao.MultiTypeDao

class MultiTypeViewModel(multiTypeDao: MultiTypeDao): BaseKotlinViewModel() {
    val items = multiTypeDao.getAll()
}