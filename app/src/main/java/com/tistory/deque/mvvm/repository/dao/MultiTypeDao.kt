package com.tistory.deque.mvvm.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tistory.deque.mvvm.model.Cat
import com.tistory.deque.mvvm.model.MultiTypeModel

@Dao
interface MultiTypeDao {
    @Query("SELECT * FROM multi_type_model")
    fun getAll(): LiveData<List<MultiTypeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(multiType: List<MultiTypeModel>)
}