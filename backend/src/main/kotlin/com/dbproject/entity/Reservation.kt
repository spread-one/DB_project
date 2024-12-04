package com.dbproject.entity

import jakarta.persistence.*

@Entity
@Table(name = "reservation")  // 데이터베이스 테이블 이름 매핑
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")  // 테이블 컬럼과 매핑
    val id: Int = 0,

    @Column(name = "reserver_name", nullable = false)
    val reserverName: String,

    @ManyToOne(fetch = FetchType.LAZY)  // 다대일 관계 설정
    @JoinColumn(name = "space_id", referencedColumnName = "space_id")  // 외래 키 매핑
    val createdSpace: CreatedSpace,

    @Column(name = "start_time", nullable = false)
    val startTime: java.time.LocalTime,

    @Column(name = "end_time", nullable = false)
    val endTime: java.time.LocalTime
)
