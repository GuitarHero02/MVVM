package com.tistory.deque.mvvm.view

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bookmark -> {
                startActivity(Intent(this, BookMarkActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}