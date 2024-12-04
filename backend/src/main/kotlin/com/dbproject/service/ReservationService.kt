package com.dbproject.service

import com.dbproject.dto.CreateReservationRequest
import com.dbproject.dto.ReservationResponse
import com.dbproject.entity.Reservation
import com.dbproject.repository.CreatedSpaceRepository
import com.dbproject.repository.ReservationRepository
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val createdSpaceRepository: CreatedSpaceRepository
) {

    // 예약 생성
    fun createReservation(request: CreateReservationRequest): ReservationResponse {
        val space = createdSpaceRepository.findById(request.spaceId)
            .orElseThrow { IllegalArgumentException("Space not found with ID: ${request.spaceId}") }

        // 중복 예약 확인
        val overlappingReservations = reservationRepository.findByStartTimeBetweenOrEndTimeBetween(
            request.startTime, request.endTime, request.startTime, request.endTime
        )
        if (overlappingReservations.isNotEmpty()) {
            throw IllegalArgumentException("Time slot already reserved")
        }

        // 새 예약 생성 및 저장
        val reservation = Reservation(
            reserverName = request.reserverName,
            createdSpace = space,
            startTime = request.startTime,
            endTime = request.endTime
        )
        val savedReservation = reservationRepository.save(reservation)

        // ReservationResponse 반환
        return ReservationResponse(
            id = savedReservation.id,
            reserverName = savedReservation.reserverName,
            startTime = savedReservation.startTime,
            endTime = savedReservation.endTime,
            createdSpaceId = space.id
        )
    }
}
