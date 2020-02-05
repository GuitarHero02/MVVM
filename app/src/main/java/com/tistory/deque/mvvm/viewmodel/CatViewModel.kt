package com.tistory.deque.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.deque.mvvm.base.BaseKotlinViewModel
import com.tistory.deque.mvvm.model.Cat
import com.tistory.deque.mvvm.repository.dao.CatDao

class CatViewModel(catDao: CatDao) : BaseKotlinViewModel(){
    var TAG = javaClass.simpleName
    var clickConverter = MutableLiveData<Unit>()
    val cats = catDao.getAll()

    //클릭 이벤트를 받아온다.
    fun onClickHandler() {
        Log.d(TAG, "클릭하면 이곳으로 옵니다.")
        clickConverter.value = Unit
    }
}