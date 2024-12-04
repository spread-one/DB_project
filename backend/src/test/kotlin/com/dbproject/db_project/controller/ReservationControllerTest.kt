package com.dbproject.controller

import com.dbproject.dto.CreateReservationRequest
import com.dbproject.dto.ReservationResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.dbproject.service.ReservationService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any // Mockito-Kotlin import
import org.mockito.kotlin.whenever // Mockito-Kotlin import
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalTime

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var reservationService: ReservationService // MockBean으로 설정

    @BeforeEach
    fun setup() {
        // ObjectMapper 설정
        objectMapper.registerModule(JavaTimeModule())

        // Mock 동작 정의
        whenever(reservationService.createReservation(any(), any())).thenReturn(
            ReservationResponse(
                id = 1,
                reserverName = "John Doe",
                startTime = LocalTime.of(10, 0),
                endTime = LocalTime.of(12, 0),
                createdSpace = 1
            )
        )
    }

    @Test
    fun `createReservation should return status 201`() {
        // Given
        val spaceId = 1
        val request = CreateReservationRequest(
            reserverName = "John Doe",
            startTime = LocalTime.of(10, 0),
            endTime = LocalTime.of(12, 0)
        )
        val requestBody = objectMapper.writeValueAsString(request)

        // When & Then
        mockMvc.perform(
            post("/api/spaces/$spaceId/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andDo(print()) // 디버깅 로그 출력
            .andExpect(status().isCreated) // 상태 코드 201 확인
    }
}