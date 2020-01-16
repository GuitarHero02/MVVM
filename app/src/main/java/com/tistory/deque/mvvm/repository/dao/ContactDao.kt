package com.tistory.deque.mvvm.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tistory.deque.mvvm.repository.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}