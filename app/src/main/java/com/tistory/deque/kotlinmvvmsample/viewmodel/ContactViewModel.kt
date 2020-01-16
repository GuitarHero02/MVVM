package com.tistory.deque.kotlinmvvmsample.viewmodel

import androidx.lifecycle.LiveData
import com.tistory.deque.kotlinmvvmsample.repository.Contact
import com.tistory.deque.kotlinmvvmsample.base.BaseKotlinViewModel
import com.tistory.deque.kotlinmvvmsample.repository.dao.ContactDao

class ContactViewModel(private val repository: ContactDao): BaseKotlinViewModel() {
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }
}