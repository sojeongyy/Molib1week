package com.example.opsidian

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opsidian.R
import com.example.opsidian.data.models.Car

class CarAdapter(
    private val carList: List<Car>,
    private val onItemClick: (Car) -> Unit
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val carImage: ImageView = itemView.findViewById
        var carLogo: ImageView = itemView.findViewById(R.id.carLogo)
        val carName: TextView = itemView.findViewById(R.id.carName)
        val carPhone: TextView = itemView.findViewById(R.id.carPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.carLogo.setImageResource(car.logoResId)
        holder.carName.text = car.name
        holder.carPhone.text = car.phone

        holder.itemView.setOnClickListener {
            onItemClick(car)
        }
    }

    override fun getItemCount(): Int {
        return carList.size
    }
}