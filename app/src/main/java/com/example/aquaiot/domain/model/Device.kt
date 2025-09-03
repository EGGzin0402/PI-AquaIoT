package com.example.aquaiot.domain.model

data class Device(
    val name: String,
    val nextTime: String,
    val status: Boolean,
    val temperature: Number,
    val ph: Number
)
