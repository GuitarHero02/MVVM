package com.tistory.deque.kotlinmvvmsample.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tistory.deque.kotlinmvvmsample.repository.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}