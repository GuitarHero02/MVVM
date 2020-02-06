package com.tistory.deque.mvvm.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tistory.deque.mvvm.model.CardItem
import com.tistory.deque.mvvm.model.Cat
import com.tistory.deque.mvvm.model.MultiTypeModel

@Dao
interface CardItemDao {
    @Query("SELECT * FROM card_item")
    fun getAll(): LiveData<List<CardItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(multiType: List<CardItem>)
}