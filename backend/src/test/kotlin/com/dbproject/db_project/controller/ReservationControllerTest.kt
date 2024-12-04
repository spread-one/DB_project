package com.dbproject.controller

import com.dbproject.dto.CreateReservationRequest
import com.dbproject.dto.ReservationResponse
import com.dbproject.service.ReservationService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@WebMvcTest(ReservationController::class)
class ReservationControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var reservationService: ReservationService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `createReservation should return 201 and reservation response`() {
        // Given
        val spaceId = 1
        val request = CreateReservationRequest(
            reserverName = "John Doe",
            startTime = LocalTime.of(9, 0),
            endTime = LocalTime.of(10, 0)
        )
        val reservationResponse = ReservationResponse(
            id = 1,
            reserverName = request.reserverName,
            startTime = request.startTime,
            endTime = request.endTime,
            createdSpace = spaceId
        )

        whenever(reservationService.createReservation(eq(spaceId), any())).thenReturn(reservationResponse)

        // When & Then
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

        mockMvc.post("/api/spaces/{spaceId}/reservations", spaceId) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.startTime") { value(reservationResponse.startTime.format(timeFormatter)) }
            jsonPath("$.endTime") { value(reservationResponse.endTime.format(timeFormatter)) }
        }


        verify(reservationService).createReservation(eq(spaceId), any())
    }

    @Test
    fun `createReservation should return 400 when request is invalid`() {
        // Given
        val spaceId = 1
        val request = CreateReservationRequest(
            reserverName = "",  // Invalid name (assuming @NotBlank or similar validation)
            startTime = LocalTime.of(9, 0),
            endTime = LocalTime.of(8, 0)  // Invalid time (end before start)
        )

        // When & Then
        mockMvc.post("/api/spaces/{spaceId}/reservations", spaceId) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isBadRequest() }
        }

        verifyNoInteractions(reservationService)
    }

    @Test
    fun `createReservation should return 400 when service throws exception`() {
        // Given
        val spaceId = 1
        val request = CreateReservationRequest(
            reserverName = "John Doe",
            startTime = LocalTime.of(9, 0),
            endTime = LocalTime.of(10, 0)
        )

        whenever(reservationService.createReservation(eq(spaceId), any()))
            .thenThrow(IllegalArgumentException("Time slot already reserved for space ID: $spaceId"))

        // When & Then
        mockMvc.post("/api/spaces/{spaceId}/reservations", spaceId) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isBadRequest() }
            content { string("Time slot already reserved for space ID: $spaceId") }
        }

        verify(reservationService).createReservation(eq(spaceId), any())
    }
}
