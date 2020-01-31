package com.tistory.deque.mvvm.viewmodel

import com.tistory.deque.mvvm.base.BaseKotlinViewModel
import com.tistory.deque.mvvm.repository.dao.CatDao

class CatListViewModel(catDao: CatDao) : BaseKotlinViewModel() {
    var TAG = javaClass.simpleName
    var cats = catDao.getAll()
}