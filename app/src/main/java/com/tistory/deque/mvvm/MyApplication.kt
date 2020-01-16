package com.tistory.deque.mvvm

import android.app.Application
import com.tistory.deque.mvvm.di.myDiModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)
    }
}