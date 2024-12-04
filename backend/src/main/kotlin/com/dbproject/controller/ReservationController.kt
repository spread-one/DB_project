package com.dbproject.controller

import com.dbproject.dto.CreateReservationRequest
import com.dbproject.dto.ReservationResponse
import com.dbproject.service.ReservationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/spaces/{spaceId}/reservations")  // 공간별 예약 API 경로
@Validated
class ReservationController(private val reservationService: ReservationService) {

    // 예약 생성
    @PostMapping
    fun createReservation(
        @PathVariable spaceId: Int,  // URL 경로에서 spaceId 읽기
        @Valid @RequestBody request: CreateReservationRequest
    ): ResponseEntity<ReservationResponse> {
        val reservationResponse = reservationService.createReservation(spaceId, request)
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationResponse)
    }
}
