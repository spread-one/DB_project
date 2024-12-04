package com.dbproject.repository  // 경로에 맞게 패키지 수정

import com.dbproject.entity.Reservation  // 수정된 엔터티 경로
import com.dbproject.entity.CreatedSpace  // 수정된 엔터티 경로
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
