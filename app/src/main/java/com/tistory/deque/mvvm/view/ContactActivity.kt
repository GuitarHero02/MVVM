package com.tistory.deque.mvvm.view

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.repository.Contact
import com.tistory.deque.mvvm.adapter.ContactAdapter
import com.tistory.deque.mvvm.base.BaseKotlinActivity
import com.tistory.deque.mvvm.databinding.ActivityContractBinding
import com.tistory.deque.mvvm.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.activity_contract.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactActivity : BaseKotlinActivity<ActivityContractBinding, ContactViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.activity_contract

    override val viewModel: ContactViewModel by viewModel()

    override fun initStartView() {}

    override fun initDataBinding() {
        val adapter = ContactAdapter({ contact ->
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NAME, contact.name)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NUMBER, contact.number)
            intent.putExtra(AddActivity.EXTRA_CONTACT_ID, contact.id)
            startActivity(intent)
        }, { contact ->
            deleteDialog(contact)
        })

        val lm = LinearLayoutManager(this)
        main_recycleview.adapter = adapter
        main_recycleview.layoutManager = lm
        main_recycleview.setHasFixedSize(true)

        viewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)
        })
    }

    override fun initAfterBinding() {
        main_button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                viewModel.delete(contact)
            }
        builder.show()
    }

}