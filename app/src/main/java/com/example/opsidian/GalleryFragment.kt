package com.example.opsidian

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsidian.data.models.CarModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GalleryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewGallery)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // JSON 데이터 로드
        val carList = loadCarsFromJson(requireContext())

        // 어댑터 설정 및 클릭 이벤트 처리
        adapter = GalleryAdapter(carList) { selectedCar ->
            // 자동차 클릭 시 상세 페이지로 이동
            val carDetailFragment = CarDetailFragment.newInstance(
                selectedCar.name,
                selectedCar.imageName,
                selectedCar.brand,
                selectedCar.launchYear,
                selectedCar.options,
                selectedCar.horsepower,
                selectedCar.fuelEconomy,
                selectedCar.price
            )
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, carDetailFragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter

        return view
    }

    private fun loadCarsFromJson(context: Context): List<CarModel> {
        return try {
            val inputStream = context.resources.openRawResource(R.raw.cars)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val gson = Gson()
            val type = object : TypeToken<List<CarModel>>() {}.type
            gson.fromJson(jsonString, type)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
