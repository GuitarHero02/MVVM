package com.tistory.deque.mvvm.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tistory.deque.mvvm.model.Cat

@Dao
interface CatDao {
    @Query("SELECT * FROM cats")
    fun getAll(): LiveData<List<Cat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cats: List<Cat>)
}