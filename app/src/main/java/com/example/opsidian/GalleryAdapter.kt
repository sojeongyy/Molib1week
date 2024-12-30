package com.example.opsidian

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.opsidian.data.models.CarModel

class GalleryAdapter(
    private val carList: List<CarModel>,
    private val onItemClick: (CarModel) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val carImageView: ImageView = view.findViewById(R.id.carImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gallery, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = carList[position]
        val context = holder.itemView.context

        // 이미지 리소스 로드
        val resourceId = context.resources.getIdentifier(
            car.imageName, "drawable", context.packageName
        )
        holder.carImageView.setImageResource(resourceId)

        // 아이템 클릭 이벤트 처리
        holder.itemView.setOnClickListener {
            onItemClick(car)
        }
    }

    override fun getItemCount(): Int = carList.size
}
