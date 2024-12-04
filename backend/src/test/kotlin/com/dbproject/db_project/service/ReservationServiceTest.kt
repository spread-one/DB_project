package com.dbproject.service

import com.dbproject.dto.CreateReservationRequest
import com.dbproject.entity.CreatedSpace
import com.dbproject.entity.Reservation
import com.dbproject.repository.CreatedSpaceRepository
import com.dbproject.repository.ReservationRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalTime
import java.util.*

class ReservationServiceTest {

    private val reservationRepository = mockk<ReservationRepository>()
    private val createdSpaceRepository = mockk<CreatedSpaceRepository>()
    private val reservationService = ReservationService(reservationRepository, createdSpaceRepository)

    @Test
    fun `createReservation should save a new reservation`() {
        // Given
        val spaceId = 1  // spaceId를 별도로 정의
        val request = CreateReservationRequest(
            reserverName = "John Doe",
            startTime = LocalTime.of(10, 0),
            endTime = LocalTime.of(12, 0)
        )
        val space = CreatedSpace(
            id = spaceId,
            description = "Room A",
            availableStartTime = LocalTime.of(8, 0),
            availableEndTime = LocalTime.of(18, 0)
        )
        val reservation = Reservation(
            id = 0,
            reserverName = request.reserverName,
            startTime = request.startTime,
            endTime = request.endTime,
            createdSpace = space
        )

        every { createdSpaceRepository.findById(spaceId) } returns Optional.of(space)
        every {
            reservationRepository.findByCreatedSpaceAndStartTimeLessThanAndEndTimeGreaterThan(
                any(), any(), any()
            )
        } returns emptyList()
        every { reservationRepository.save(any()) } returns reservation.copy(id = 1)

        // When
        val result = reservationService.createReservation(spaceId, request)  // spaceId 추가

        // Then
        assertNotNull(result)
        assertEquals("John Doe", result.reserverName)
        verify(exactly = 1) { createdSpaceRepository.findById(spaceId) }
        verify(exactly = 1) { reservationRepository.save(any()) }
    }
}
