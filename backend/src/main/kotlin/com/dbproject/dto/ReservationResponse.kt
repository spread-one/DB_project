package com.dbproject.dto

import java.time.LocalTime

data class ReservationResponse(
    val id: Int,
    val reserverName: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val createdSpace: Int
)
