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
    private val carList: List<CarModel>,
    private val onItemClick: (CarModel) -> Unit // 클릭 이벤트 전달
) : RecyclerView.Adapter<CarModelAdapter.CarViewHolder>() {

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carImageView: ImageView = itemView.findViewById(R.id.carImageView)
        val carNameTextView: TextView = itemView.findViewById(R.id.carNameTextView)

        fun bind(car: CarModel) {
            carNameTextView.text = car.name

            val resourceId = itemView.context.resources.getIdentifier(
                car.imageName, "drawable", itemView.context.packageName
            )
            carImageView.setImageResource(resourceId)

            // 아이템 클릭 이벤트 처리
            itemView.setOnClickListener {
                onItemClick(car)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carList[position])
    }

    override fun getItemCount(): Int = carList.size
}
