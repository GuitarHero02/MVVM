package com.tistory.deque.mvvm.util

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.model.Cat
import com.tistory.deque.mvvm.model.MultiTypeModel
import com.tistory.deque.mvvm.model.enum.MultiType
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

                    Log.e("CHECK INTO HERE", database.getCatDao().getAll().toString())

                    val list = mutableListOf<MultiTypeModel>().apply {
                        add(MultiTypeModel(id = 1, type = MultiType.TEXT_TYPE.type, text = "이승찬님의 핀트투자는 \n3,000,000,000원 \n입니다.", data = 0, contentString = null))
                        add(MultiTypeModel(id = 2, type = MultiType.IMAGE_TYPE.type, text = "텍스트뷰 아래에 이미지가 있는 뷰타입.", data = R.drawable.snow, contentString = null))
                        add(MultiTypeModel(id = 3, type = MultiType.IMAGE_TYPE_2.type, text = "안녕, 제목부분이 될거야", data = R.drawable.snow, contentString = "내용부분!"))
                        add(MultiTypeModel(id = 4, type = MultiType.IMAGE_TYPE.type, text = "다시 한 번 텍스트 옆에 이미지가 있는 뷰타입", data = R.drawable.snow, contentString = null))
                        add(MultiTypeModel(id = 5, type = MultiType.IMAGE_TYPE_2.type, text = "제목2!!", data = R.drawable.snow, contentString = "사진에 대한 설명?"))

                        add(MultiTypeModel(id = 6, type = MultiType.TEXT_TYPE.type, text = "카테고리 2번!", data = 0, contentString = null))
                        add(MultiTypeModel(id = 7, type = MultiType.IMAGE_TYPE.type, text = "새로운 카테고리 시작!.", data = R.drawable.snow, contentString = null))
                        add(MultiTypeModel(id = 8, type = MultiType.IMAGE_TYPE.type, text = "다음생엔 울창한 숲의 이름모를 나무로 태어나 평화로이 살다가 누군가의 유서가 되고 싶다.", data = R.drawable.snow, contentString = null))
                        add(MultiTypeModel(id = 9, type = MultiType.IMAGE_TYPE_2.type, text = "제목부분.", data = R.drawable.snow, contentString = "내용부분"))
                    }
                    database.getMultiTypeDao().insertAll(list)
                    Log.e("CHECK INTO HERE2", database.getMultiTypeDao().getAll().toString())

                    Result.success()
                }
            }


        } catch (e: Exception) {
            Log.d(TAG, "Error cat worker", e)
            Result.failure()

        }
    }
}