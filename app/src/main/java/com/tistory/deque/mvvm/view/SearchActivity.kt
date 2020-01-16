package com.tistory.deque.mvvm.view

import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.base.BaseKotlinActivity
import com.tistory.deque.mvvm.databinding.ActivitySearchBinding
import com.tistory.deque.mvvm.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseKotlinActivity<ActivitySearchBinding, SearchViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_search

    override val viewModel: SearchViewModel by viewModel()

    override fun initStartView() {
        viewDataBinding.vm = viewModel
        viewDataBinding.setLifecycleOwner(this)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }
}