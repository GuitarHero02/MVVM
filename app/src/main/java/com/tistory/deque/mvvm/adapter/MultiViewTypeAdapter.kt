package com.tistory.deque.mvvm.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.tistory.deque.mvvm.R
import com.tistory.deque.mvvm.model.MultiTypeModel
import com.tistory.deque.mvvm.model.enum.MultiType

class MultiViewTypeAdapter(private val list: List<MultiTypeModel>, private val action:() -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var totalTypes = list.size

    // getItemViewType의 리턴값 Int가 viewType으로 넘어온다.
    // viewType으로 넘어오는 값에 따라 viewHolder를 알맞게 처리해주면 된다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when (viewType) {
            MultiType.TEXT_TYPE.type -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.text_type, parent, false)
                view.setOnClickListener { action() }
                TextTypeViewHolder(view)
            }
            MultiType.IMAGE_TYPE.type -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.image_type, parent, false)
                view.setOnClickListener { action() }
                ImageTypeViewHolder(view)
            }
            MultiType.IMAGE_TYPE_2.type -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.image_type2, parent, false)
                view.setOnClickListener { action() }
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
            MultiType.TEXT_TYPE.type -> (holder as TextTypeViewHolder).txtType.text = obj.text
            MultiType.IMAGE_TYPE.type -> {
                (holder as ImageTypeViewHolder).title.text = obj.text
                holder.image.setImageResource(obj.data)
                val entries = arrayListOf<Entry>()
                entries.add(Entry(0f,4f ))
                entries.add(Entry(1f,8f))
                entries.add(Entry(2f,6f))
                entries.add(Entry(3f,2f))
                entries.add(Entry(4f,18f))
                entries.add(Entry(5f,9f))
                entries.add(Entry(6f,16f))
                entries.add(Entry(7f,5f))
                entries.add(Entry(8f,3f))
                entries.add(Entry(10f,7f))
                entries.add(Entry(11f,9f))

                val dataset = LineDataSet(entries, "Sample Data")

//                dataset.setCircleColorHole(R.color.white)
//                dataset.setCircleColor(R.color.chartLineColor)
//                dataset.lineWidth = 9f
//                dataset.setColor(R.color.chartLineColor, 100)

                val data = LineData(dataset)
//                data.setValueTextColor(R.color.chartLineColor)
                data.setValueTextSize(10f)

                holder.lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
                holder.lineChart.xAxis.setDrawGridLines(false)
                holder.lineChart.xAxis.setDrawAxisLine(false)
                holder.lineChart.xAxis.setDrawLabels(false)

                holder.lineChart.axisLeft.setDrawLabels(false)
                holder.lineChart.axisLeft.setDrawAxisLine(false)
                holder.lineChart.axisLeft.setDrawGridLines(false)

                holder.lineChart.axisRight.setDrawLabels(false)
                holder.lineChart.axisRight.setDrawAxisLine(false)
                holder.lineChart.axisRight.setDrawGridLines(false)

                holder.lineChart.data = data
                holder.lineChart.animateY(500)
            }
            MultiType.IMAGE_TYPE_2.type -> {
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
        val lineChart: LineChart = itemView.findViewById(R.id.item_line_chart)
    }

    inner class ImageTypeView2Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleView)
        val content: TextView = itemView.findViewById(R.id.contentView)
        val image: ImageView = itemView.findViewById(R.id.imageView2)
    }

}