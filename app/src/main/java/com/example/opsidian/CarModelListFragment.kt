package com.example.opsidian

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsidian.data.models.CarModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarModelListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarModelAdapter

    // 검색창 입력 필드
    private lateinit var searchInput: EditText

    // 검색어
    private var queryBrand: String? = null

    companion object {
        private const val ARG_QUERY = "arg_query"

        // CarModelListFragment 생성 시 검색어 전달하는 함수
        fun newInstance(query: String): CarModelListFragment {
            val fragment = CarModelListFragment()
            val args = Bundle()
            args.putString(ARG_QUERY, query)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // HomeFragment에서 넘긴 검색어 받기
        queryBrand = arguments?.getString(ARG_QUERY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_car_model_list, container, false)

        // 검색창과 RecyclerView 초기화
        searchInput = view.findViewById(R.id.home_search_input)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // JSON 로드 & 초기 필터링
        val carListAll = loadCarsFromJson(requireContext())
        val initialFilteredList = if (!queryBrand.isNullOrEmpty()) {
            carListAll.filter { car -> car.brand.contains(queryBrand!!, ignoreCase = true) }
        } else {
            carListAll
        }

        // 어댑터 설정 - 클릭 이벤트 추가
        adapter = CarModelAdapter(initialFilteredList) { selectedCar ->
            // 아이템 클릭 시 상세 페이지로 이동
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

        // 검색창 이벤트 처리
        searchInput.addTextChangedListener { text ->
            val query = text?.toString()?.trim() ?: ""
            val filteredList = if (query.isNotEmpty()) {
                carListAll.filter { car -> car.brand.contains(query, ignoreCase = true) }
            } else {
                carListAll
            }
            adapter = CarModelAdapter(filteredList) { selectedCar ->
                // 필터링 후에도 클릭 이벤트 처리
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
        }

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

