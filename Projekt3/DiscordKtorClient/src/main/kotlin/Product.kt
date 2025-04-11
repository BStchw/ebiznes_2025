package com.example.discord

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double
)
