package com.tistory.deque.mvvm.view

import android.util.TypedValue
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.adapter.MultiViewTypeAdapter
import com.tistory.deque.mvvm.base.BaseKotlinActivity
import com.tistory.deque.mvvm.databinding.ActivityPersonalInfoBinding
import com.tistory.deque.mvvm.viewmodel.MultiTypeViewModel
import kotlinx.android.synthetic.main.activity_personal_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalInfoActivity : BaseKotlinActivity<ActivityPersonalInfoBinding, MultiTypeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_personal_info

    override val viewModel: MultiTypeViewModel by viewModel()

    override fun initStartView() {
        val bottomSheetBehavior = BottomSheetBehavior.from(ll_bottom_sheet)
        bottomSheetBehavior.peekHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150f, resources.displayMetrics).toInt()
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
                if(p1 <= 0.0f){
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

            override fun onStateChanged(p0: View, p1: Int) {
                //prevent scroll
                if (p1 == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                } else if(p1 == BottomSheetBehavior.STATE_COLLAPSED){

                }
            }
        })

        ll_bottom_sheet.setOnClickListener {
            if(bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        viewModel.items.observe(this, Observer {
            rv_profile_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            val adapter = MultiViewTypeAdapter(it, action = {
                if(bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                    val handler: android.os.Handler = android.os.Handler()
                    handler.postDelayed({
                        run {
                            rv_profile_list?.smoothScrollToPosition(0)
                        }
                    }, 200)
//                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                } else {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            })
            rv_profile_list.adapter = adapter
            rv_profile_list.adapter?.notifyDataSetChanged()
        })
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
    }
}