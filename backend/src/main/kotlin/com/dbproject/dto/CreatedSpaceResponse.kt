package com.dbproject.dto

import java.time.LocalTime

data class CreatedSpaceResponse(
    val id: Int,
    val description: String,
    val availableStartTime: LocalTime,
    val availableEndTime: LocalTime,
    val reservations: List<SimpleReservationResponse> = listOf()
)
