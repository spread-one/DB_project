package com.dbproject.controller

import com.dbproject.dto.CreateReservationRequest
import com.dbproject.dto.ReservationResponse
import com.dbproject.service.ReservationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reservations")  // API 기본 경로
class ReservationController(private val reservationService: ReservationService) {

    // 예약 생성
    @PostMapping
    fun createReservation(@RequestBody request: CreateReservationRequest): ResponseEntity<ReservationResponse> {
        val reservationResponse = reservationService.createReservation(request)
        return ResponseEntity.ok(reservationResponse)
    }
}
