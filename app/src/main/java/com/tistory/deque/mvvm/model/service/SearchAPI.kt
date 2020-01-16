package com.tistory.deque.mvvm.model.service

import com.tistory.deque.mvvm.model.response.RepositoriesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author Leopold
 */
interface SearchAPI {
    @GET("/search/repositories")
    fun search(@QueryMap params: MutableMap<String, String>): Single<RepositoriesResponse>
}