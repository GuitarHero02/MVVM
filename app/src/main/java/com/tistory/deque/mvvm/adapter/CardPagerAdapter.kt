package com.tistory.deque.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.model.CardItem

class CardPagerAdapter : PagerAdapter(), CardAdapter {
    private var mCardView:MutableList<CardView?> = mutableListOf()
    private var mData:MutableList<CardItem> = mutableListOf()
    private var mBaseElevation:Float = 0f

    fun addCardItem(item:CardItem){
        mCardView.add(null)
        mData.add(item)
    }

    override fun isViewFromObject(view: View, ob: Any): Boolean {
        return view === ob
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getCardViewAt(position: Int): CardView? {
        return mCardView.get(position)
    }

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.adapter, container, false)
        container.addView(view)
        bind(mData[position], view)
        val cardView = view.findViewById(R.id.cardView) as CardView

        if (mBaseElevation == 0f) {
            mBaseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = mBaseElevation * MAX_ELEVATION_FACTOR
        mCardView.set(position, cardView)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
        mCardView.set(position, null)
    }

    private fun bind(item: CardItem, view: View) {
        val imageContent = view.findViewById(R.id.image_content) as ImageView
        val contentTextView = view.findViewById(R.id.content_text) as TextView
        val background = view.findViewById<LinearLayout>(R.id.ll_card_layout)
        background.setBackgroundColor(ContextCompat.getColor(view.context, item.background))
        imageContent.setImageDrawable(ContextCompat.getDrawable(view.context, item.image))
        contentTextView.setText(item.title)
    }
}