package com.dbproject.controller

import com.dbproject.entity.Reservation
import com.dbproject.service.ReservationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalTime

@RestController
@RequestMapping("/api/reservations")  // API 기본 경로
class ReservationController(private val reservationService: ReservationService) {

    // 예약 생성
    @PostMapping
    fun createReservation(
        @RequestParam spaceId: Int,
        @RequestParam reserverName: String,
        @RequestParam startTime: String,
        @RequestParam endTime: String
    ): ResponseEntity<Reservation> {
        val reservation = reservationService.createReservation(
            spaceId,
            reserverName,
            LocalTime.parse(startTime),
            LocalTime.parse(endTime)
        )
        return ResponseEntity.ok(reservation)
    }
}
