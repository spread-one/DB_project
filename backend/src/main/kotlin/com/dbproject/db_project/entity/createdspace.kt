package com.yourproject.entity

import jakarta.persistence.*

@Entity
@Table(name = "created_space")  // 데이터베이스 테이블 이름 매핑
data class CreatedSpace(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "space_id")  // 테이블 컬럼과 매핑
    val id: Int = 0,

    @Column(name = "description", nullable = false)  // NOT NULL 제약조건
    val description: String,

    @Column(name = "available_start_time", nullable = false)
    val availableStartTime: java.time.LocalTime,

    @Column(name = "available_end_time", nullable = false)
    val availableEndTime: java.time.LocalTime
)

@OneToMany(mappedBy = "createdSpace", cascade = [CascadeType.ALL], orphanRemoval = true)
val reservations: List<Reservation> = mutableListOf()
