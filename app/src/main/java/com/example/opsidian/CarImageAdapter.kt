package com.example.opsidian

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.opsidian.data.models.CarModel

class CarImageAdapter(private val carList: List<CarModel>) :
    RecyclerView.Adapter<CarImageAdapter.CarViewHolder>() {

    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val carImage: ImageView = view.findViewById(R.id.carImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gallery, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.carImage.setImageResource(
            holder.itemView.context.resources.getIdentifier(
                car.imageName,
                "drawable",
                holder.itemView.context.packageName
            )
        )
    }

    override fun getItemCount(): Int = carList.size
}
