package com.dbproject.service

import com.dbproject.dto.CreateReservationRequest
import com.dbproject.dto.ReservationResponse
import com.dbproject.entity.CreatedSpace
import com.dbproject.entity.Reservation
import com.dbproject.repository.CreatedSpaceRepository
import com.dbproject.repository.ReservationRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.time.LocalTime
import java.util.*

class ReservationServiceTest {

    private val reservationRepository: ReservationRepository = mock()
    private val createdSpaceRepository: CreatedSpaceRepository = mock()

    private lateinit var reservationService: ReservationService

    @BeforeEach
    fun setUp() {
        reservationService = ReservationService(reservationRepository, createdSpaceRepository)
    }

    @Test
    fun `createReservation should create a reservation successfully`() {
        // Given
        val spaceId = 1
        val createdSpace = CreatedSpace(
            id = spaceId,
            description = "Conference Room",
            availableStartTime = LocalTime.of(8, 0),
            availableEndTime = LocalTime.of(18, 0),
            reservations = mutableListOf()
        )
        val request = CreateReservationRequest(
            reserverName = "John Doe",
            startTime = LocalTime.of(9, 0),
            endTime = LocalTime.of(10, 0)
        )
        val reservation = Reservation(
            id = 1,
            reserverName = request.reserverName,
            createdSpace = createdSpace,
            startTime = request.startTime,
            endTime = request.endTime
        )

        whenever(createdSpaceRepository.findById(spaceId)).thenReturn(Optional.of(createdSpace))
        whenever(
            reservationRepository.findByCreatedSpaceAndStartTimeLessThanAndEndTimeGreaterThan(
                createdSpace,
                request.startTime,
                request.endTime
            )
        ).thenReturn(emptyList())
        whenever(reservationRepository.save(any<Reservation>())).thenReturn(reservation)

        // When
        val response = reservationService.createReservation(spaceId, request)

        // Then
        assertNotNull(response)
        assertEquals(reservation.id, response.id)
        assertEquals(reservation.reserverName, response.reserverName)
        assertEquals(reservation.startTime, response.startTime)
        assertEquals(reservation.endTime, response.endTime)
        assertEquals(reservation.createdSpace.id, response.createdSpace)

        verify(createdSpaceRepository).findById(spaceId)
        verify(reservationRepository).findByCreatedSpaceAndStartTimeLessThanAndEndTimeGreaterThan(
            createdSpace,
            request.startTime,
            request.endTime
        )
        verify(reservationRepository).save(any<Reservation>())
    }


    @Test
    fun `createReservation should throw exception when space not found`() {
        // Given
        val spaceId = 1
        val request = CreateReservationRequest(
            reserverName = "Jane Doe",
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(12, 0)
        )

        whenever(createdSpaceRepository.findById(spaceId)).thenReturn(Optional.empty())

        // When & Then
        val exception = assertThrows(IllegalArgumentException::class.java) {
            reservationService.createReservation(spaceId, request)
        }
        assertEquals("Space not found with ID: $spaceId", exception.message)

        verify(createdSpaceRepository).findById(spaceId)
        verifyNoMoreInteractions(reservationRepository)
    }

    @Test
    fun `createReservation should throw exception when time slot is already reserved`() {
        // Given
        val spaceId = 1
        val createdSpace = CreatedSpace(
            id = spaceId,
            description = "Conference Room",
            availableStartTime = LocalTime.of(8, 0),
            availableEndTime = LocalTime.of(18, 0),
            reservations = mutableListOf()
        )
        val request = CreateReservationRequest(
            reserverName = "John Doe",
            startTime = LocalTime.of(9, 0),
            endTime = LocalTime.of(10, 0)
        )
        val overlappingReservation = Reservation(
            id = 2,
            reserverName = "Existing User",
            createdSpace = createdSpace,
            startTime = LocalTime.of(9, 30),
            endTime = LocalTime.of(10, 30)
        )

        whenever(createdSpaceRepository.findById(spaceId)).thenReturn(Optional.of(createdSpace))
        whenever(
            reservationRepository.findByCreatedSpaceAndStartTimeLessThanAndEndTimeGreaterThan(
                createdSpace,
                request.startTime,
                request.endTime
            )
        ).thenReturn(listOf(overlappingReservation))

        // When & Then
        val exception = assertThrows(IllegalArgumentException::class.java) {
            reservationService.createReservation(spaceId, request)
        }
        assertEquals("Time slot already reserved for space ID: $spaceId", exception.message)

        verify(createdSpaceRepository).findById(spaceId)
        verify(reservationRepository).findByCreatedSpaceAndStartTimeLessThanAndEndTimeGreaterThan(
            createdSpace,
            request.startTime,
            request.endTime
        )
    }
}
