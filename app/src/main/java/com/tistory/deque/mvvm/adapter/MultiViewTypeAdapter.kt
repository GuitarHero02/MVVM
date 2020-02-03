package com.tistory.deque.mvvm.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.model.MultiTypeModel

class MultiViewTypeAdapter(private val list: MutableList<MultiTypeModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var totalTypes = list.size

    // getItemViewType의 리턴값 Int가 viewType으로 넘어온다.
    // viewType으로 넘어오는 값에 따라 viewHolder를 알맞게 처리해주면 된다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when (viewType) {
            MultiTypeModel.TEXT_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.text_type, parent, false)
                TextTypeViewHolder(view)
            }
            MultiTypeModel.IMAGE_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.image_type, parent, false)
                ImageTypeViewHolder(view)
            }
            MultiTypeModel.IMAGE_TYPE_2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.image_type2, parent, false)
                ImageTypeView2Holder(view)
            }
            else -> throw RuntimeException("알 수 없는 뷰 타입 에러")
        }
    }

    override fun getItemCount(): Int {
        return totalTypes
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("MultiViewTypeAdapter", "Hi, onBindViewHolder")
        val obj = list[position]
        when (obj.type) {
            MultiTypeModel.TEXT_TYPE -> (holder as TextTypeViewHolder).txtType.text = obj.text
            MultiTypeModel.IMAGE_TYPE -> {
                (holder as ImageTypeViewHolder).title.text = obj.text
                holder.image.setImageResource(obj.data)
            }
            MultiTypeModel.IMAGE_TYPE_2 -> {
                (holder as ImageTypeView2Holder).title.text = obj.text
                holder.content.text = obj.contentString
                holder.image.setImageResource(obj.data)
            }
        }
    }

    // 여기서 받는 position은 데이터의 index다.
    override fun getItemViewType(position: Int): Int {
        Log.d("MultiViewTypeAdapter", "Hi, getItemViewType")
        return list[position].type
    }

    inner class TextTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtType: TextView = itemView.findViewById(R.id.title)
    }

    inner class ImageTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.background)
    }

    inner class ImageTypeView2Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleView)
        val content: TextView = itemView.findViewById(R.id.contentView)
        val image: ImageView = itemView.findViewById(R.id.imageView2)
    }

}