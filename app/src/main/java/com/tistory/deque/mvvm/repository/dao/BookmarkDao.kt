package com.tistory.deque.mvvm.repository.dao

import androidx.paging.DataSource
import androidx.room.*
import com.tistory.deque.mvvm.repository.Bookmark

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM Bookmark ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, Bookmark>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmark: Bookmark)

    @Delete
    fun delete(bookmark: Bookmark)

}