package com.dbproject.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalTime

data class CreateSpaceRequest(
    @field:NotBlank(message = "Description cannot be blank.")
    @field:Size(max = 255, message = "Description must not exceed 255 characters.")
    val description: String,

    @field:NotNull(message = "Available start time is required.")
    val availableStartTime: LocalTime,

    @field:NotNull(message = "Available end time is required.")
    val availableEndTime: LocalTime
)
