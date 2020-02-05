package com.tistory.deque.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "multi_type_model")
data class MultiTypeModel(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "type") val type: Int = 0,
    @ColumnInfo(name = "text") val text: String = "",
    @ColumnInfo(name = "data") val data: Int = 0,
    @ColumnInfo(name = "content_string") val contentString: String?)