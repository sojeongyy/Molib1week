package com.example.opsidian

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsidian.data.models.CarModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarModelListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarModelAdapter

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
        redirectSystemOutToLogcat()
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        // 1. JSON 로드 & 전체 자동차 목록 가져오기
        val carListAll = loadCarsFromJson(requireContext())
        println(carListAll)

        // 2. 검색어가 있다면 필터링
        val filteredList = if (!queryBrand.isNullOrEmpty()) {
            var result = carListAll.filter { car ->
                car.brand.contains(queryBrand!!, ignoreCase = true)
            }
            // 터미널에 출력
            println("검색어: $queryBrand")
            result.forEach { car ->
                println("필터링된 자동차: ${car.name}")
            }
            result
        } else {
            // 검색어가 없으면 전체 목록
            carListAll
        }

        // 3. 어댑터 생성 후 연결
        adapter = CarModelAdapter(filteredList)
        recyclerView.adapter = adapter

        return view
    }

    private fun redirectSystemOutToLogcat() {
        System.setOut(object : java.io.PrintStream(System.out) {
            override fun println(message: String?) {
                Log.d(TAG, message ?: "null")
            }
        })
    }
    // JSON 파일에서 데이터 읽어오는 함수
    private fun loadCarsFromJson(context: Context): List<CarModel> {
        redirectSystemOutToLogcat()
        return try {
            val inputStream = context.resources.openRawResource(R.raw.cars)
            println("json 겅공")
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
