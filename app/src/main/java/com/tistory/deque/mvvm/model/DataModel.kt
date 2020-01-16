package com.tistory.deque.mvvm.model

import com.tistory.deque.mvvm.model.enum.KakaoSearchSortEnum
import com.tistory.deque.mvvm.model.response.ImageSearchResponse
import io.reactivex.Single

interface DataModel {
    fun getData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int): Single<ImageSearchResponse>
}