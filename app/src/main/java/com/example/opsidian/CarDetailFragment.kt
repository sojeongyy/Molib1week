package com.example.opsidian

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class CarDetailFragment : Fragment() {

    companion object {
        private const val ARG_NAME = "arg_name"
        private const val ARG_IMAGE_NAME = "arg_image_name"
        private const val ARG_BRAND = "arg_brand"
        private const val ARG_LAUNCH_YEAR = "arg_launch_year"
        private const val ARG_OPTIONS = "arg_options"
        private const val ARG_HORSEPOWER = "arg_horsepower"
        private const val ARG_FUEL_ECONOMY = "arg_fuel_economy"
        private const val ARG_PRICE = "arg_price"

        // 데이터를 개별적으로 전달하는 메서드
        fun newInstance(
            name: String,
            imageName: String,
            brand: String,
            launchYear: String,
            options: String,
            horsepower: String,
            fuelEconomy: String,
            price: String
        ): CarDetailFragment {
            val fragment = CarDetailFragment()
            val args = Bundle().apply {
                putString(ARG_NAME, name)
                putString(ARG_IMAGE_NAME, imageName)
                putString(ARG_BRAND, brand)
                putString(ARG_LAUNCH_YEAR, launchYear)
                putString(ARG_OPTIONS, options)
                putString(ARG_HORSEPOWER, horsepower)
                putString(ARG_FUEL_ECONOMY, fuelEconomy)
                putString(ARG_PRICE, price)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_car_detail, container, false)

        // 전달받은 데이터 가져오기
        val name = arguments?.getString(ARG_NAME)
        val imageName = arguments?.getString(ARG_IMAGE_NAME)
        val brand = arguments?.getString(ARG_BRAND)
        val launchYear = arguments?.getString(ARG_LAUNCH_YEAR)
        val options = arguments?.getString(ARG_OPTIONS)
        val horsepower = arguments?.getString(ARG_HORSEPOWER)
        val fuelEconomy = arguments?.getString(ARG_FUEL_ECONOMY)
        val price = arguments?.getString(ARG_PRICE)

        // UI 요소 초기화
        val carImageView = view.findViewById<ImageView>(R.id.car_image)
        val carNameTextView = view.findViewById<TextView>(R.id.car_name)
        val carBrandTextView = view.findViewById<TextView>(R.id.car_brand)
        val carLaunchYearTextView = view.findViewById<TextView>(R.id.car_launch_year)
        val carOptionsTextView = view.findViewById<TextView>(R.id.car_options)
        val carHorsepowerTextView = view.findViewById<TextView>(R.id.car_horsepower)
        val carFuelEconomyTextView = view.findViewById<TextView>(R.id.car_fuel_economy)
        val carPriceTextView = view.findViewById<TextView>(R.id.car_price)

        // UI에 데이터 설정
        carNameTextView.text = name
        carBrandTextView.text = brand
        carLaunchYearTextView.text = getString(R.string.car_launch_year, launchYear)
        carOptionsTextView.text = getString(R.string.car_options, options)
        carHorsepowerTextView.text = getString(R.string.car_horsepower, horsepower)
        carFuelEconomyTextView.text = getString(R.string.car_fuel_economy, fuelEconomy)
        carPriceTextView.text = getString(R.string.car_price, price)

        // 이미지 설정
        val resourceId = requireContext().resources.getIdentifier(
            imageName, "drawable", requireContext().packageName
        )
        carImageView.setImageResource(resourceId)

        return view
    }
}
