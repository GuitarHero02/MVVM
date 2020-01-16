package com.tistory.deque.mvvm.model.response

import com.google.gson.annotations.SerializedName
import com.tistory.deque.mvvm.model.Repository

/**
 * @author Leopold
 */
data class RepositoriesResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val repositories: ArrayList<Repository>
)