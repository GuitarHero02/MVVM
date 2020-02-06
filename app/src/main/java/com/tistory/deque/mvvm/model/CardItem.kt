package com.tistory.deque.mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_item")
data class CardItem (
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    val title:Int,
    val image:Int,
    val background:Int
)