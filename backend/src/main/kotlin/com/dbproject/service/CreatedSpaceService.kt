package com.dbproject.service  // 경로에 맞게 패키지 수정

import com.dbproject.entity.CreatedSpace  // 수정된 엔터티 경로
import com.dbproject.repository.CreatedSpaceRepository  // 수정된 리포지토리 경로
import org.springframework.stereotype.Service

@Service
class CreatedSpaceService(private val createdSpaceRepository: CreatedSpaceRepository) {

    // 모든 공간 조회
    fun getAllSpaces(): List<CreatedSpace> {
        return createdSpaceRepository.findAll()
    }

    // 특정 설명으로 공간 검색
    fun findSpaceByDescription(description: String): List<CreatedSpace> {
        return createdSpaceRepository.findByDescription(description)
    }
}
