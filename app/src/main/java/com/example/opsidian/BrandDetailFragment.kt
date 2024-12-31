package com.example.opsidian

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsidian.CarImageAdapter
import com.example.opsidian.data.models.CarModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_brand_detail, container, false)

        // 전달받은 데이터 가져오기
        val args = arguments
        val brand = args?.getString("brandName") ?: "" // 직영점이름
        val brandSite = args?.getString("brandSite") ?: ""
        val brandAddress = args?.getString("brandAddress") ?: ""
        val brandPhone = args?.getString("brandPhone") ?: ""
        val brandLogoResId = args?.getInt("brandLogoResId") ?: R.drawable.mclaren
        val brandString = findBrandString(brand)

        val carList = loadCars()


        // 뷰에 데이터 표시
        view.findViewById<ImageView>(R.id.brandLogo).setImageResource(brandLogoResId)
        view.findViewById<TextView>(R.id.brandName).text = brand
        view.findViewById<TextView>(R.id.brandSite).text = brandSite
        view.findViewById<TextView>(R.id.brandAddress).text = brandAddress
        view.findViewById<TextView>(R.id.brandString).text = brandString

        // 전화번호 버튼 설정
        val phoneButton = view.findViewById<Button>(R.id.brandPhoneButton)
        phoneButton.text = brandPhone
        phoneButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$brandPhone")
            }
            startActivity(intent)
        }

        // RecyclerView 설정
        val carRecyclerView = view.findViewById<RecyclerView>(R.id.carRecyclerView)
        carRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // JSON 데이터 로드 및 필터링

        val filteredCarList = carList.filter { car ->
            car.brand.equals(brandString, ignoreCase = true) // brand와 비교
        }

        // RecyclerView 어댑터 설정
        if (filteredCarList.isNotEmpty()) {
            val adapter = CarImageAdapter(filteredCarList)
            carRecyclerView.adapter = adapter
        } else {
            println("No cars found for brand: $brandString")
        }

        return view
    }

    private fun loadCars(): List<CarModel> {
        val inputStream = resources.openRawResource(R.raw.cars)
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<CarModel>>() {}.type
        return Gson().fromJson(reader, type)
    }
    private fun findBrandString(brandName: String): String {
        val brandMappings = mapOf(
            "Lamborghini Seoul" to "Lamborghini",
            "Porsche Gangnam" to "Porsche",
            "Maybach Gangnam" to "Maybach",
            "RollsRoyce Seoul" to "Rolls-Royce",
            "McLaren Seoul" to "McLaren",
            "Bugatti Seoul" to "Bugatti"
        )
        return brandMappings[brandName] ?: "Unknown"
    }
}
