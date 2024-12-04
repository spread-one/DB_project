package com.yourproject.repository

import com.yourproject.entity.Reservation
import com.yourproject.entity.CreatedSpace
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalTime

@Repository
interface ReservationRepository : JpaRepository<Reservation, Int> {
    // 특정 공간의 예약 목록 조회
    fun findByCreatedSpace(createdSpace: CreatedSpace): List<Reservation>

    // 특정 시간대에 예약 겹침 여부 확인
    fun findByStartTimeBetweenOrEndTimeBetween(
        startTime: LocalTime,
        endTime: LocalTime,
        overlapStartTime: LocalTime,
        overlapEndTime: LocalTime
    ): List<Reservation>
}
