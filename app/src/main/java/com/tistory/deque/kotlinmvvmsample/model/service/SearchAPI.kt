package com.tistory.deque.kotlinmvvmsample.model.service

import com.tistory.deque.kotlinmvvmsample.model.response.RepositoriesResponse
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