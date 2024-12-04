package com.dbproject.repository

import com.dbproject.entity.CreatedSpace
import com.dbproject.entity.Reservation
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalTime

@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private lateinit var createdSpaceRepository: CreatedSpaceRepository

    @Autowired
    private lateinit var reservationRepository: ReservationRepository

    @BeforeEach
    fun setup() {
        reservationRepository.deleteAll()
        createdSpaceRepository.deleteAll()
    }

    @Test
    fun `findByCreatedSpace should return reservations for a specific space`() {
        // Given
        val space = createdSpaceRepository.save(
            CreatedSpace(
                description = "Room A",
                availableStartTime = LocalTime.of(8, 0),
                availableEndTime = LocalTime.of(18, 0)
            )
        )
        reservationRepository.save(
            Reservation(
                reserverName = "John Doe",
                startTime = LocalTime.of(10, 0),
                endTime = LocalTime.of(12, 0),
                createdSpace = space
            )
        )

        // When
        val reservations = reservationRepository.findByCreatedSpace(space)

        // Then
        assertEquals(1, reservations.size)
        assertEquals("John Doe", reservations[0].reserverName)
    }
}
