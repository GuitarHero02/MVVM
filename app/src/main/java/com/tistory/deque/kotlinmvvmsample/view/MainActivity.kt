package com.tistory.deque.kotlinmvvmsample.view

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tistory.deque.kotlinmvvmsample.MainSearchRecyclerViewAdapter
import com.tistory.deque.kotlinmvvmsample.R
import com.tistory.deque.kotlinmvvmsample.base.BaseKotlinActivity
import com.tistory.deque.kotlinmvvmsample.databinding.ActivityMainBinding
import com.tistory.deque.kotlinmvvmsample.model.response.ImageSearchResponse
import com.tistory.deque.kotlinmvvmsample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    private val mainSearchRecyclerViewAdapter: MainSearchRecyclerViewAdapter by inject()

    override fun initStartView() {
        main_activity_search_recycler_view.run {
            adapter = mainSearchRecyclerViewAdapter
            layoutManager = StaggeredGridLayoutManager(3, 1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.imageSearchResponseLiveData.observe(this, Observer {
            it.documents.forEach {document ->
                mainSearchRecyclerViewAdapter.addImageItem(document.image_url, document.doc_url)
            }
            mainSearchRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        main_activity_search_button.setOnClickListener {
            viewModel.getImageSearch(main_activity_search_text_view.text.toString(), 1, 80)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.contact -> {
                startActivity(Intent(this, ContactActivity::class.java))
                true
            }
            R.id.bookmark -> {
                startActivity(Intent(this, ContactActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
