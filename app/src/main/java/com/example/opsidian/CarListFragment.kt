package com.example.opsidian

import CustomDividerItem
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsidian.data.models.Car
import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.yourapp.utils.MarginItemDecoration
import org.json.JSONArray

/**
 * A simple [Fragment] subclass.
 * Use the [CarListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_car_list, container, false)

        // 브랜드 이름과 설명 설정
        val brandTitle = view.findViewById<TextView>(R.id.brandTitle)
        val brandSubtitle = view.findViewById<TextView>(R.id.brandSubtitle)

        brandTitle.text = "Obsidian"
        brandSubtitle.text = "국내 자동차 직영점 전화번호"

        // JSON 데이터 읽기
        val carList = readJsonData(requireContext())

        // RecyclerView 설정
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CarAdapter(carList) { selectedCar ->
            // 아이템 클릭 시 DetailFragment로 이동
            navigateToDetail(selectedCar)
        }

        val dividerHeight = resources.getDimensionPixelSize(R.dimen.divider_spacing)
        val paddingAbove = resources.getDimensionPixelSize(R.dimen.divider_padding_above)
        val paddingBelow = resources.getDimensionPixelSize(R.dimen.divider_padding_below)
        val dividerColor = ContextCompat.getColor(requireContext(), R.color.divider_color) // 구분선 색상
        val paddingColor = ContextCompat.getColor(requireContext(), R.color.padding_color) // 여백 색상

        recyclerView.addItemDecoration(
            CustomDividerItem(dividerHeight, dividerColor, paddingAbove, paddingBelow, paddingColor)
        )

        return view
    }

    private fun navigateToDetail(car: Car) {
        val detailFragment = DetailFragment()
        val bundle = Bundle().apply {
            putString("brandName", car.name)
            putString("brandSite", car.site)
            putString("brandAddress", car.address)
            putString("brandPhone", car.phone)
            putInt("brandLogoResId", car.logoResId)
        }
        detailFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailFragment) // MainActivity의 fragmentContainer ID를 사용
            .addToBackStack(null)
            .commit()
    }
}

fun readJsonData(context: Context): List<Car> {
    // JSON 파일 읽기
    val inputStream = context.resources.openRawResource(R.raw.carbrands)
    val jsonText = inputStream.bufferedReader().use { it.readText() }

    val jsonArray = JSONArray(jsonText)
    val carList = mutableListOf<Car>()

    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val car = Car(
            name = jsonObject.getString("name"),
            phone = jsonObject.getString("phone"),
            logoResId = context.resources.getIdentifier(
                jsonObject.getString("logo"), "drawable", context.packageName
            ),
            site = jsonObject.getString("site"),
            address = jsonObject.getString("address"),
            carImageResId = context.resources.getIdentifier(
                jsonObject.optString("carImage", ""), "drawable", context.packageName
            )
        )
        carList.add(car)
    }

    return carList
}

