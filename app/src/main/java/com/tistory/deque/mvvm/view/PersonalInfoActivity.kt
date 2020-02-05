package com.tistory.deque.mvvm.view

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.adapter.CatListAdapter
import com.tistory.deque.mvvm.adapter.MultiViewTypeAdapter
import com.tistory.deque.mvvm.base.BaseKotlinActivity
import com.tistory.deque.mvvm.databinding.ActivityPersonalInfoBinding
import com.tistory.deque.mvvm.model.MultiTypeModel
import com.tistory.deque.mvvm.model.enum.MultiType
import com.tistory.deque.mvvm.viewmodel.CatViewModel
import com.tistory.deque.mvvm.viewmodel.MultiTypeViewModel
import kotlinx.android.synthetic.main.activity_personal_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalInfoActivity : BaseKotlinActivity<ActivityPersonalInfoBinding, MultiTypeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_personal_info

    override val viewModel: MultiTypeViewModel by viewModel()

    override fun initStartView() {
        viewModel.items.observe(this, Observer {
            rv_profile_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            Log.e("CHECK LSIT", it.toString())
            val adapter = MultiViewTypeAdapter(it)
            rv_profile_list.adapter = adapter
            rv_profile_list.adapter?.notifyDataSetChanged()
        })
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }
}