package com.example.opsidian

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_brand_detail, container, false)

        // 전달받은 데이터 가져오기
        val args = arguments
        val brandName = args?.getString("brandName") ?: ""
        val brandSite = args?.getString("brandSite") ?: ""
        val brandAddress = args?.getString("brandAddress") ?: ""
        val brandPhone = args?.getString("brandPhone") ?: ""
        val brandLogoResId = args?.getInt("brandLogoResId") ?: R.drawable.mclaren

        // 뷰에 데이터 표시
        view.findViewById<ImageView>(R.id.brandLogo).setImageResource(brandLogoResId)
        view.findViewById<TextView>(R.id.brandName).text = brandName
        view.findViewById<TextView>(R.id.brandSite).text = brandSite
        view.findViewById<TextView>(R.id.brandAddress).text = brandAddress
        view.findViewById<TextView>(R.id.brandPhone).text = brandPhone

        val phoneTextView = view.findViewById<TextView>(R.id.brandPhone)
        phoneTextView.text = brandPhone

        // 전화번호 클릭 시 전화 앱으로 이동
        phoneTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$brandPhone")
            }
            startActivity(intent)
        }

        return view
    }
}
