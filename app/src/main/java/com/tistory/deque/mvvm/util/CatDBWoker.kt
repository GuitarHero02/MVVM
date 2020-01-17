package com.tistory.deque.mvvm.util

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.tistory.deque.mvvm.model.Cat
import com.tistory.deque.mvvm.repository.ContactDatabase

const val INIT_CAT_JSON = "cats.json"

class CatDBWoker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    var TAG = javaClass.simpleName

    override fun doWork(): Result {
        return try {
            applicationContext.assets.open(INIT_CAT_JSON).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<ArrayList<Cat>>() {}.type
                    val cats: ArrayList<Cat> = Gson().fromJson(jsonReader, type)

                    val database = ContactDatabase.getInstance(applicationContext)
                    database.getCatDao().insertAll(cats)

                    Result.success()
                }
            }


        } catch (e: Exception) {
            Log.d(TAG, "Error cat worker", e)
            Result.failure()

        }
    }
}