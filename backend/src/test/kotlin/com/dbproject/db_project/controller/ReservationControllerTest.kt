package com.dbproject.controller

import com.dbproject.dto.CreateReservationRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule  // 추가된 부분
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalTime  // 추가된 부분

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    init {
        // LocalTime 직렬화를 위한 설정
        objectMapper.registerModule(JavaTimeModule())
    }

    @Test
    fun `createReservation should return status 201`() {
        // Given
        val spaceId = 1  // spaceId를 별도로 정의
        val request = CreateReservationRequest(
            reserverName = "John Doe",
            startTime = LocalTime.of(10, 0),
            endTime = LocalTime.of(12, 0)
        )
        val requestBody = objectMapper.writeValueAsString(request)

        // When & Then
        mockMvc.perform(
            post("/api/spaces/$spaceId/reservations")  // URL에 spaceId 포함
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isCreated)  // 상태 코드 201 확인
    }
}
