package com.dbproject.dto

import java.time.LocalTime

data class SimpleReservationResponse(
    val id: Int,
    val reserverName: String,
    val startTime: LocalTime,
    val endTime: LocalTime
)
