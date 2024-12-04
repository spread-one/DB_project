package com.dbproject.service

import com.dbproject.dto.CreateReservationRequest
import com.dbproject.dto.ReservationResponse
import com.dbproject.entity.Reservation
import com.dbproject.repository.CreatedSpaceRepository
import com.dbproject.repository.ReservationRepository
import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val createdSpaceRepository: CreatedSpaceRepository
) {

    // 예약 생성
    fun createReservation(spaceId: Int, request: CreateReservationRequest): ReservationResponse {
        // 공간 조회
        val createdSpace = createdSpaceRepository.findById(spaceId)
            .orElseThrow { IllegalArgumentException("Space not found with ID: $spaceId") }

        // 중복 예약 확인 (공간 기준)
        val overlappingReservations = reservationRepository.findByCreatedSpaceAndStartTimeLessThanAndEndTimeGreaterThan(
            createdSpace,
            request.startTime,
            request.endTime
        )
        if (overlappingReservations.isNotEmpty()) {
            throw IllegalArgumentException("Time slot already reserved for space ID: $spaceId")
        }

        // 새 예약 생성 및 저장
        val reservation = Reservation(
            reserverName = request.reserverName,
            createdSpace = createdSpace,
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
            createdSpace = savedReservation.createdSpace.id
        )
    }
}
