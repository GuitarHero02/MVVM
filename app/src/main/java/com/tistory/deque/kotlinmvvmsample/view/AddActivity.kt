package com.tistory.deque.kotlinmvvmsample.view

import android.widget.Toast
import com.tistory.deque.kotlinmvvmsample.R
import com.tistory.deque.kotlinmvvmsample.base.BaseKotlinActivity
import com.tistory.deque.kotlinmvvmsample.databinding.ActivityAddBinding
import com.tistory.deque.kotlinmvvmsample.repository.Contact
import com.tistory.deque.kotlinmvvmsample.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.activity_add.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddActivity : BaseKotlinActivity<ActivityAddBinding, ContactViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.activity_add

    override val viewModel: ContactViewModel by viewModel()
    private var id: Long? = null

    override fun initStartView() {
        // intent null check & get extras
        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(EXTRA_CONTACT_NUMBER)
            && intent.hasExtra(EXTRA_CONTACT_ID)) {
            add_edittext_name.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            add_edittext_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
        add_button.setOnClickListener {
            val name = add_edittext_name.text.toString().trim()
            val number = add_edittext_number.text.toString()

            if (name.isEmpty() || number.isEmpty()) {
                Toast.makeText(this, "Please enter name and number.", Toast.LENGTH_SHORT).show()
            } else {
                val initial = name[0].toUpperCase()
                val contact = Contact(id, name, number, initial)
                viewModel.insert(contact)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }
}
