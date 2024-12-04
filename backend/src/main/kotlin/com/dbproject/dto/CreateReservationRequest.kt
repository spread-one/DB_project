package com.dbproject.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalTime

data class CreateReservationRequest(
    @field:NotBlank(message = "Reserver name cannot be blank")
    val reserverName: String,

    @field:NotNull(message = "Start time is required")
    val startTime: LocalTime,

    @field:NotNull(message = "End time is required")
    val endTime: LocalTime
)
