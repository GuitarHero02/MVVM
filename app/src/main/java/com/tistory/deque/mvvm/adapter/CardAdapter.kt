package com.tistory.deque.mvvm.adapter

import androidx.cardview.widget.CardView

const val MAX_ELEVATION_FACTOR = 8

interface CardAdapter {

    abstract fun getBaseElevation(): Float

    abstract fun getCardViewAt(position: Int): CardView?

    abstract fun getCount(): Int
}