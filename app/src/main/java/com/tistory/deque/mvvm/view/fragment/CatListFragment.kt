package com.tistory.deque.mvvm.view.fragment

import android.util.Log
import androidx.lifecycle.Observer
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.adapter.CatListAdapter
import com.tistory.deque.mvvm.base.BaseKotlinFragment
import com.tistory.deque.mvvm.databinding.FragmentCatListBinding
import com.tistory.deque.mvvm.viewmodel.CatListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatListFragment : BaseKotlinFragment<FragmentCatListBinding, CatListViewModel>() {
    private var TAG = javaClass.simpleName

    override val layoutResourceId: Int
        get() = R.layout.fragment_cat_list

    override val viewModel: CatListViewModel by viewModel()

    override fun initDataBinding() {
        viewModel.cats.observe(this, Observer {
            Log.d(TAG, "onsubscribe:: ${it}")
            val catListAdapter = CatListAdapter()
            catListAdapter.submitList(it)
            viewDataBinding.rvCatList.adapter = catListAdapter
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatListFragment()
    }
}
