package com.dbproject.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime

data class ReservationResponse(
    val id: Int,
    val reserverName: String,
    @JsonFormat(pattern = "HH:mm:ss")
    val startTime: LocalTime,
    @JsonFormat(pattern = "HH:mm:ss")
    val endTime: LocalTime,
    val createdSpace: Int
)
