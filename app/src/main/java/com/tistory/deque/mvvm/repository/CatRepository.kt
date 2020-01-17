package com.tistory.deque.mvvm.repository

import com.tistory.deque.mvvm.repository.dao.CatDao


class CatRepository private constructor(private val catDao: CatDao) {
    fun getAllCats() = catDao.getAll()

    companion object {
        @Volatile
        private var instance: CatRepository? = null

        fun getInstance(catDao: CatDao) =
            instance ?: synchronized(this) {
                instance ?: CatRepository(catDao).also { instance = it }
            }

    }
}