package com.tistory.deque.mvvm.view

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.ysmg.adm.util.extend.replaceFragment
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.adapter.MultiViewTypeAdapter
import com.tistory.deque.mvvm.base.BaseKotlinActivity
import com.tistory.deque.mvvm.databinding.ActivityCatBinding
import com.tistory.deque.mvvm.databinding.ActivityPersonalInfoBinding
import com.tistory.deque.mvvm.model.MultiTypeModel
import com.tistory.deque.mvvm.model.User
import com.tistory.deque.mvvm.view.fragment.CatListFragment
import com.tistory.deque.mvvm.viewmodel.CatViewModel
import kotlinx.android.synthetic.main.activity_personal_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalInfoActivity : BaseKotlinActivity<ActivityPersonalInfoBinding, CatViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_personal_info

    override val viewModel: CatViewModel by viewModel()

    override fun initStartView() {
        val list = mutableListOf<MultiTypeModel>().apply {
            add(MultiTypeModel(MultiTypeModel.TEXT_TYPE, "카테고리 1번!", 0, null))
            add(MultiTypeModel(MultiTypeModel.IMAGE_TYPE, "텍스트뷰 아래에 이미지가 있는 뷰타입.", R.drawable.snow, null))
            add(MultiTypeModel(MultiTypeModel.IMAGE_TYPE_2, "안녕, 제목부분이 될거야", R.drawable.snow, "내용부분!"))
            add(MultiTypeModel(MultiTypeModel.IMAGE_TYPE, "다시 한 번 텍스트 옆에 이미지가 있는 뷰타입", R.drawable.snow, null))
            add(MultiTypeModel(MultiTypeModel.IMAGE_TYPE_2, "제목2!!", R.drawable.snow, "사진에 대한 설명?"))

            add(MultiTypeModel(MultiTypeModel.TEXT_TYPE, "카테고리 2번!", 0, null))
            add(MultiTypeModel(MultiTypeModel.IMAGE_TYPE, "새로운 카테고리 시작!.", R.drawable.snow, null))
            add(MultiTypeModel(MultiTypeModel.IMAGE_TYPE, "다음생엔 울창한 숲의 이름모를 나무로 태어나 평화로이 살다가 누군가의 유서가 되고 싶다.", R.drawable.snow, null))
            add(MultiTypeModel(MultiTypeModel.IMAGE_TYPE_2, "제목부분.", R.drawable.snow, "내용부분"))
        }

        val adpater = MultiViewTypeAdapter(list)
        rv_profile_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_profile_list.adapter = adpater
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }
}