package com.tistory.deque.mvvm.di

import com.google.gson.GsonBuilder
import com.tistory.deque.mvvm.BuildConfig
import com.tistory.deque.mvvm.MainSearchRecyclerViewAdapter
import com.tistory.deque.mvvm.repository.ContactDatabase
import com.tistory.deque.mvvm.model.DataModel
import com.tistory.deque.mvvm.model.DataModelImpl
import com.tistory.deque.mvvm.model.service.KakaoSearchService
import com.tistory.deque.mvvm.model.service.SearchAPI
import com.tistory.deque.mvvm.repository.CatRepository
import com.tistory.deque.mvvm.viewmodel.*
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

var retrofitPart = module {
    single<KakaoSearchService> {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoSearchService::class.java)
    }
}

val gitNetworkModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }

    single { GsonBuilder().create() }

    single {
        OkHttpClient.Builder().apply {
            cache(get())
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(get())
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

    single {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("Accept", "application/vnd.github.mercy-preview+json")
            }.build())
        }
    }
}

var apiModule = module {
    single(createOnStart = false) { get<Retrofit>().create(SearchAPI::class.java) }
}

var adapterPart = module {
    factory {
        MainSearchRecyclerViewAdapter()
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        ContactViewModel(get())
    }
    viewModel {
        SearchViewModel(get(), get())
    }
    viewModel {
        BookMarkViewModel(get())
    }
    viewModel {
        CatViewModel(get())
    }
}

val databaseModule = module {
    single { ContactDatabase.getInstance(androidApplication()) }
    single { get<ContactDatabase>().getContactDao() }
    single(createOnStart = false) { get<ContactDatabase>().getBookmarkDao()}
    single { get<ContactDatabase> ().getCatDao()}
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart, databaseModule, gitNetworkModule, apiModule)