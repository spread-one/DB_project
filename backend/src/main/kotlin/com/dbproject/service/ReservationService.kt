package com.dbproject.service  // 경로에 맞게 패키지 수정

import com.dbproject.entity.Reservation  // 수정된 엔터티 경로
import com.dbproject.repository.ReservationRepository  // 수정된 리포지토리 경로
import com.dbproject.repository.CreatedSpaceRepository  // 수정된 리포지토리 경로
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val createdSpaceRepository: CreatedSpaceRepository
) {

    // 예약 생성
    fun createReservation(spaceId: Int, reserverName: String, startTime: LocalTime, endTime: LocalTime): Reservation {
        val space = createdSpaceRepository.findById(spaceId)
            .orElseThrow { IllegalArgumentException("Space not found with ID: $spaceId") }

        // 예약 시간 중복 확인
        val overlappingReservations = reservationRepository.findByStartTimeBetweenOrEndTimeBetween(
            startTime, endTime, startTime, endTime
        )
        if (overlappingReservations.isNotEmpty()) {
            throw IllegalArgumentException("Time slot already reserved")
        }

        val reservation = Reservation(
            reserverName = reserverName,
            createdSpace = space,
            startTime = startTime,
            endTime = endTime
        )
        return reservationRepository.save(reservation)
    }
}
