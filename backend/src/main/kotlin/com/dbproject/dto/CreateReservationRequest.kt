package com.dbproject.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalTime

data class CreateReservationRequest(
    @field:NotNull(message = "Space ID is required.")
    val spaceId: Int,

    @field:NotBlank(message = "Reserver name cannot be blank.")
    @field:Size(max = 100, message = "Reserver name must not exceed 100 characters.")
    val reserverName: String,

    @field:NotNull(message = "Start time is required.")
    val startTime: LocalTime,

    @field:NotNull(message = "End time is required.")
    val endTime: LocalTime
)
