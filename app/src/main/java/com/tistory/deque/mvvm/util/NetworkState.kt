package com.tistory.deque.mvvm.util

sealed class NetworkState<out T> {
    class Init : NetworkState<Nothing>()
    class Loading : NetworkState<Nothing>()
    class Success<out T>(val item: T) : NetworkState<T>()
    class Error(val threowable: Throwable?) : NetworkState<Nothing>()
}