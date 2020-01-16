package com.tistory.deque.mvvm.view

import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.base.BaseKotlinActivity
import com.tistory.deque.mvvm.databinding.ActivityBookmarkBinding
import com.tistory.deque.mvvm.viewmodel.BookMarkViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookMarkActivity : BaseKotlinActivity<ActivityBookmarkBinding, BookMarkViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.activity_bookmark

    override val viewModel: BookMarkViewModel by viewModel()

    override fun initStartView() {
        viewDataBinding.vm = viewModel
        viewDataBinding.setLifecycleOwner(this)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }
}