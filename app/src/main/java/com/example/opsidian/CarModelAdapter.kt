package com.example.opsidian

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opsidian.data.models.CarModel

class CarModelAdapter(
    private var carList: List<CarModel>
) : RecyclerView.Adapter<CarModelAdapter.CarViewHolder>() {

    // ViewHolder
    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carImageView: ImageView = itemView.findViewById(R.id.carImageView)
        val carNameTextView: TextView = itemView.findViewById(R.id.carNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.carNameTextView.text = car.name

        // 이미지 리소스 동적 로딩
        val context = holder.itemView.context
        val resourceId = context.resources.getIdentifier(
            car.imageName,
            "drawable",
            context.packageName
        )
        if (resourceId == 0) {
            Log.e("CarModelAdapter", "리소스를 찾을 수 없습니다: ${car.imageName}")
        } else {
            holder.carImageView.setImageResource(resourceId)
        }

        holder.carImageView.setImageResource(resourceId)
    }

    override fun getItemCount(): Int = carList.size

    // 새로 필터링된 데이터를 적용하기 위해 함수 추가
    fun updateData(newList: List<CarModel>) {
        carList = newList
        notifyDataSetChanged()
    }
}
