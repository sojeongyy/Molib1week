package com.example.opsidian.data.models
// 브랜드
data class Car(
    val name: String,
    val phone: String,
    val logoResId: Int,        // 로고 이미지 (R.drawable)
    val site: String,          // 웹사이트 URL
    val address: String,       // 주소
    val carImageResId: Int     // 자동차 이미지 (R.drawable)
)
