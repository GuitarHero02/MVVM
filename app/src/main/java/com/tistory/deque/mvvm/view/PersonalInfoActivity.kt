package com.tistory.deque.mvvm.view

import android.widget.Toast
import androidx.lifecycle.Observer
import com.android.ysmg.adm.util.extend.replaceFragment
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.base.BaseKotlinActivity
import com.tistory.deque.mvvm.databinding.ActivityCatBinding
import com.tistory.deque.mvvm.databinding.ActivityPersonalInfoBinding
import com.tistory.deque.mvvm.model.User
import com.tistory.deque.mvvm.view.fragment.CatListFragment
import com.tistory.deque.mvvm.viewmodel.CatViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalInfoActivity : BaseKotlinActivity<ActivityPersonalInfoBinding, CatViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_personal_info

    override val viewModel: CatViewModel by viewModel()

    override fun initStartView() {
        subscribeUi(viewDataBinding)
        setView(viewDataBinding)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun subscribeUi(binding: ActivityPersonalInfoBinding) {

        viewModel.clickConverter.observe(this, Observer {
            Toast.makeText(this, "${binding.user?.name}, ${binding.user?.address}", Toast.LENGTH_SHORT).show()
        })

        binding.user = User("이승찬", "서울시", R.drawable.profile_sample)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }

    private fun setView(binding: ActivityPersonalInfoBinding) {
        replaceFragment(this, binding.flContainer.id, CatListFragment.newInstance())
    }
}