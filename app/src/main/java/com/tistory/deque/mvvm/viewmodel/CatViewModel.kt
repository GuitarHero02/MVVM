package com.tistory.deque.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tistory.deque.mvvm.base.BaseKotlinViewModel

class CatViewModel : BaseKotlinViewModel(){
    var TAG = javaClass.simpleName
    var clickConverter = MutableLiveData<Unit>()

    //클릭 이벤트를 받아온다.
    fun onClickHandler() {
        Log.d(TAG, "클릭을하면 이곳으로 옵니다.")
        clickConverter.value = Unit
    }
}