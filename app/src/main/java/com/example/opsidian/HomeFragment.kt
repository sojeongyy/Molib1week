package com.example.opsidian

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_home.xml 기반으로 뷰 생성
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 검색창 / 버튼 참조
        searchEditText = view.findViewById(R.id.home_search_input)
        searchButton = view.findViewById(R.id.home_search_button)

        // 검색 버튼 클릭 이벤트
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            if (query.isNotEmpty()) {
                // CarModelListFragment로 이동하면서 검색어(query) 넘기기
                val carListFragment = CarModelListFragment.newInstance(query)
                println(query)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, carListFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(requireContext(), "브랜드명을 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
