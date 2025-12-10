package com.example.sustentabilidade.data.model

data class Tips(
    val id: String,
    val title: String,
    val summary: String
)

data class TipsResponse(
    val data: List<Tips>
)
